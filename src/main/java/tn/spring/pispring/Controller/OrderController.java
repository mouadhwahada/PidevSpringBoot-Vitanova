package tn.spring.pispring.Controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.*;
import tn.spring.pispring.Repositories.OrderRepository;
import tn.spring.pispring.Repositories.PaymentRepository;
import tn.spring.pispring.Repositories.UserRepo;
import tn.spring.pispring.Services.*;
import tn.spring.pispring.dtos.PaymentDto;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
@RestController

@RequestMapping("/order")
public class OrderController {


    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductService productService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    private QRCodeService qrCodeService;


    @PostMapping("/addorder")
    public Orderr addOrderItem(@RequestBody Orderr Order) {

        return orderService.addOrderr(Order);
    }

    @GetMapping("/retrieveOrder")
    public List<Orderr> retrieveOrderr() {
        return orderService.retrieveOrderr();
    }

    @PutMapping("/updateOrder/{id}")
    public Orderr updateOrder(@PathVariable int id, @RequestBody Orderr order) {
        return orderService.updateOrderr(id, order);
    }

    @GetMapping("/retrieveOrder/{id}")
    public Orderr retrieveOrder(@PathVariable int id) {
        return orderService.retrieveOrderr(id);
    }

    @DeleteMapping("/removeOrder/{id}")
    public void removeOrder(@PathVariable int id) {
        orderService.removeOrder(id);
    }


    @GetMapping("/{orderId}/orderItems")
    public List<OrderItem> getOrderItemsByOrderId(@PathVariable int orderId) {
        return orderService.getOrderItemsByOrderId(orderId);
    }


    @PostMapping("/create")
    public Orderr createOrderrForUser(@RequestParam int userId, @RequestBody List<OrderItem> orderItems) {
        return orderService.createOrderrForUser(userId, orderItems);
    }


    @GetMapping("/{idOrder}/total")
    public ResponseEntity<Float> calculateOrderTotal(@PathVariable int idOrder) {
        double total = orderService.calculateOrderTotal(idOrder);
        return ResponseEntity.ok((float) total);
    }

    private final StripeService stripeService;
    @PostMapping("/checkout/{iduser}")
    public ResponseEntity<PaymentDto> checkout(@PathVariable Long iduser) throws StripeException {
        {

                // Récupérer l'utilisateur par son ID
                User user = userRepo.findById(iduser).orElse(null);

                // Récupérer la commande de l'utilisateur
                Orderr order = orderRepository.findByUserAndStatus(user, OrderStatus.PROCESSING);
                if (order == null) {
                    return ResponseEntity.notFound().build(); // Retourner 404 si la commande n'est pas trouvée
                }

                // Calculer le montant total de la commande
                BigDecimal totalAmount = BigDecimal.valueOf(order.getCostOrder());
                PaymentIntent paymentIntent = stripeService.createPaymentIntent(totalAmount);

                // Créer un objet PaymentIntent avec le montant total

                // Mettre à jour le statut de la commande en "IN_TRANSIT"
                order.setStatus(OrderStatus.IN_TRANSIT);
                String orderCode = generateOrderCode(order.getIdOrder()); // Générer un code de commande unique
                order.setCodeOrder(orderCode);
                orderRepository.save(order); // Sauvegarder la commande mise à jour

                // Créer un objet PaymentDto avec les détails du paiement

                // Enregistrer le paiement et d'autres opérations liées à la commande...

                PaymentDto paymentDto = new PaymentDto(paymentIntent.getClientSecret(), totalAmount, "usd",order.getIdOrder());

            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.setPaid(true);
                orderItemService.updateOrderItem(orderItem); // Mettre à jour l'OrderItem dans la base de données
            }

            for (OrderItem orderItem : order.getOrderItems()) {
                Product product = orderItem.getProduct();
                int orderedQuantity = orderItem.getQuantity();
// Gérer le cas où la quantité commandée est supérieure au stock disponible

                // Décrémenter la quantité en stock du produit
                product.setStockQuantity(product.getStockQuantity() - orderedQuantity);
                // Sauvegarder les modifications du produit
                productService.updateProducts(product);
                Payment payment = new Payment();
                payment.setAmount(totalAmount);
                payment.setCurrency("usd");
                payment.setOrderr(order);
                payment.setUsername(order.getUser().getUserName());
                payment.setEmail(order.getUser().getEmail());
                paymentRepository.save(payment);

            }


            return ResponseEntity.ok().body(paymentDto);
        }
    }




    private String generateOrderCode(int orderId) {
        String uuid = UUID.randomUUID().toString();
        // Concaténer l'identifiant d'ordre avec un segment de l'UUID
        return String.valueOf(orderId) + "_" + uuid.substring(0, 8); // Utilisation des 8 premiers caractères de l'UUID
    }



    @GetMapping("/total-count")
    public long getTotalOrderCount() {
        return orderService.getTotalOrderCount();
    }





    @GetMapping("/total-amount")
    public ResponseEntity<BigDecimal> getTotalPaymentAmount() {
        BigDecimal totalAmount = orderService.getTotalPaymentAmount();
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/totalProductsSoldInTransit")
    public int getTotalProductsSold() {
        return orderService.calculateTotalProductsSoldInTransit();
    }




    @GetMapping("/productsSoldInTransit")
    public List<Product> getProductsSoldInTransit() {
        return orderService.getProductsSoldInTransit();
    }

    @GetMapping("/count/{status}")
    public ResponseEntity<Long> getOrderCountByStatus(@PathVariable("status") String status) {
        // Convertir la chaîne de statut en enum OrderStatus
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());

        // Vérifier si le statut est valide

        // Appeler la méthode du service pour récupérer le nombre d'ordres selon le statut
        long count = orderService.getCountByStatus(orderStatus);

        return ResponseEntity.ok(count);
    }

}