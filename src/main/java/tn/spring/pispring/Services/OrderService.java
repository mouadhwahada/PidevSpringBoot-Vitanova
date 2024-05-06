package tn.spring.pispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.*;
import tn.spring.pispring.Interfaces.OrderrInterface;
import tn.spring.pispring.Repositories.OrderRepository;
import tn.spring.pispring.Repositories.PaymentRepository;
import tn.spring.pispring.Repositories.UserRepo;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


@Service
public class OrderService implements OrderrInterface {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepo userRepo;

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    MailService mailService;

    @Override
    public Orderr addOrderr(Orderr orderr) {
        if (orderr.getCodeOrder() == null || orderr.getCodeOrder().isEmpty()) {
            //    generateUniqueCodeOrder(orderr, 10); // Générer un code d'ordre de longueur 8 (à ajuster selon vos besoins)
        }
        return orderRepository.save(orderr);
    }

    @Override
    public List<Orderr> retrieveOrderr() {
        return orderRepository.findAll();
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        Orderr orderr = orderRepository.findById(orderId).orElse(null);
        if (orderr != null) {
            return orderr.getOrderItems();
        } else {
            // Gérer le cas où l'ordre avec l'ID donné n'existe pas
            return null;
        }
    }

    @Override
    public List<Orderr> retrieveOrderrbyuser() {
        return null;
    }

    @Override
    public Orderr updateOrderr(Orderr order) {
        return orderRepository.save(order);
    }

    @Override
    public Orderr updateOrderr(int id, Orderr order) {
        Optional<Orderr> existingOrderItem = orderRepository.findById(id);
        if (existingOrderItem.isPresent()) {
            order.setIdOrder(id);
            return orderRepository.save(order);
        } else {
            return null;
        }
    }


    @Override
    public Orderr retrieveOrderr(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void removeOrder(int id) {
        orderRepository.deleteById(id);

    }


    public List<OrderItem> getOrderItemsForOrderr(int orderrId) {
        // Récupérer l'Orderr spécifique depuis la base de données
        Orderr orderr = orderRepository.findById(orderrId).orElse(null);

        // Vérifier si l'Orderr existe
        if (orderr != null) {
            // Retourner la liste des OrderItem de l'Orderr
            return orderr.getOrderItems();
        } else {
            // Si l'Orderr n'existe pas, renvoyer null ou une liste vide selon votre besoin
            return null;
        }
    }

    public Orderr createOrderrForUser(int userId, List<OrderItem> orderItems) {

        User user = userRepo.findById((long) userId).orElse(null);

        // Vérifier s'il existe déjà un Orderr en cours pour cet utilisateur
        Orderr existingOrderr = orderRepository.findByUserAndStatus(user, OrderStatus.PROCESSING);
        // Vérifier s'il existe déjà un Orderr en cours pour cet utilisateur

        // Si aucun Orderr en cours n'existe, créer un nouvel Orderr
        if (existingOrderr == null) {
            Orderr newOrderr = new Orderr();
            newOrderr.setUser(user);
            newOrderr.setOrderItems(orderItems);
            newOrderr.setDateOrder(Date.valueOf(LocalDate.now()));
            newOrderr.setStatus(OrderStatus.PROCESSING); // Statut de commande en cours
            // Calculez le coût total de la commande et définissez-le
            // newOrderr.setCostOrder(...);

            // Enregistrez le nouvel Orderr dans la base de données
            return orderRepository.save(newOrderr);
        } else {
            // Si un Orderr en cours existe, ajoutez simplement les OrderItem à cet Orderr
            existingOrderr.getOrderItems().addAll(orderItems);
            // Mettez à jour les détails de la commande, par exemple le coût total si nécessaire
            // existingOrderr.setCostOrder(...);

            // Enregistrez les modifications dans la base de données
            return orderRepository.save(existingOrderr);
        }


    }


    public float calculateOrderTotal(int idOrder) {
        Orderr order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        float total = 0; // Initialisez le total à 0

        for (OrderItem orderItem : order.getOrderItems()) {
            total += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }

        // Mettez à jour le coût total de la commande dans l'objet Orderr
        order.setCostOrder(total);

        // Sauvegardez l'objet Orderr mis à jour dans la base de données
        orderRepository.save(order);

        return total;
    }


    public long getTotalOrderCount() {
        return orderRepository.count();
    }


    public BigDecimal getTotalPaymentAmount() {
        return paymentRepository.getTotalPaymentAmount();
    }


    public int calculateTotalProductsSoldInTransit() {
        int totalProductsSoldInTransit = 0;

        // Récupérer toutes les commandes avec le statut "IN_Transit"
        Iterable<Orderr> orders = orderRepository.findByStatus(OrderStatus.IN_TRANSIT);

        // Parcourir toutes les commandes
        for (Orderr order : orders) {
            // Récupérer tous les éléments de commande dans la commande actuelle
            for (OrderItem orderItem : order.getOrderItems()) {
                // Ajouter la quantité de chaque élément de commande vendu au total
                totalProductsSoldInTransit += orderItem.getQuantity();
            }
        }

        return totalProductsSoldInTransit;
    }







    public List<Product> getProductsSoldInTransit() {
        List<Product> productsSoldInTransit = new ArrayList<>();
        Set<Product> uniqueProducts = new HashSet<>(); // Set to store unique products

        // Récupérer toutes les commandes avec le statut "IN_Transit"
        Iterable<Orderr> orders = orderRepository.findByStatus(OrderStatus.IN_TRANSIT);

        // Parcourir toutes les commandes
        for (Orderr order : orders) {
            // Récupérer tous les éléments de commande dans la commande actuelle
            for (OrderItem orderItem : order.getOrderItems()) {
                // Vérifier si le produit n'a pas déjà été ajouté
                if (!uniqueProducts.contains(orderItem.getProduct())) {
                    // Ajouter le produit à la liste des produits vendus en transit
                    productsSoldInTransit.add(orderItem.getProduct());
                    uniqueProducts.add(orderItem.getProduct()); // Ajouter le produit à l'ensemble des produits uniques
                }
            }
        }

        return productsSoldInTransit;
    }






    public long getCountByStatus(OrderStatus status) {
        return orderRepository.countByStatus(status);
    }




    public Orderr trackOrderByCode(String codeOrder) {
        // Récupérer la commande en fonction de son code de commande
        Orderr order = orderRepository.findByCodeOrder(codeOrder);

        // Vérifier si la commande existe
        if (order != null) {
            return order;
        } else {
            // Gérer le cas où la commande n'existe pas
            return null;
        }
    }

    public List<OrderItem> getOrderItemsByCodeOrder(String codeOrder) {
        // Récupérer la commande en fonction de son code de commande
        Orderr order = orderRepository.findByCodeOrder(codeOrder);

        // Vérifier si la commande existe
        if (order != null) {
            // Récupérer les éléments de commande associés à cette commande
            return order.getOrderItems();
        } else {
            // Gérer le cas où la commande n'existe pas
            return null;
        }
    }



    public Orderr assignDeliveryManToOrder(String codeOrder, int deliveryManPhoneNumber) {
        Orderr order = orderRepository.findByCodeOrder(codeOrder);
        if (order != null) {
            if (order.getDeliveryMan() != null) {
                // Livreur déjà assigné à la commande
                return null;
            }
            User deliveryMan = userRepo.findByPhoneNumber(deliveryManPhoneNumber);
            if (deliveryMan != null) {
                // Mettre à jour le livreur de la commande
                order.setDeliveryMan(deliveryMan);
                if (deliveryMan.getEmail() != null) {
                    try {
                        mailService.sendEmail(deliveryMan.getEmail(), "Assign a delivery man to your order",
                                "Dear " + deliveryMan.getUsername() + ",<br><br>"
                                        + "You have been assigned to an order. Please proceed accordingly.<br><br>"
                                        + "Thank you.");
                    } catch (MessagingException e) {
                        // Gérer l'erreur d'envoi d'email
                        return null;
                    }
                }
                return orderRepository.save(order);
            } else {
                // Gérer le cas où le livreur n'est pas trouvé
                return null;
            }
        } else {
            // Gérer le cas où la commande n'est pas trouvée
            return null;
        }
    }



    public List<Orderr> searchOrderByCode(String code) {
        return orderRepository.findByCodeOrderContainingIgnoreCase(code);
    }






    public List<Orderr> findAllOrdersWithDeliveryMan() {
        return orderRepository.findAllWithDeliveryMan();
    }

    public User findDeliveryManByOrderId(int orderId) {
        return orderRepository.findDeliveryManByOrderId(orderId);
    }



    public List<Orderr> getOrdersByDeliveryManId(Long deliveryManId) {
        User deliveryMan = userRepo.findById(deliveryManId).orElse(null);
        if (deliveryMan != null) {
            return orderRepository.findByDeliveryMan(deliveryMan);
        } else {
            // Gérer le cas où le livreur n'est pas trouvé
            return null;
        }
    }



    public ResponseEntity<Orderr> confirmOrderArrivalByDeliveryMan(int orderId) {
        Optional<Orderr> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Orderr order = orderOptional.get();
            order.setConfirmedByDeliveryMan(true);
            orderRepository.save(order);

            // Envoyer un e-mail au client pour l'informer de l'arrivée de la commande
            try {
                String confirmationLink = "http://localhost:4200/confirmOrder"; // Lien vers la page de confirmation de la commande
                mailService.sendEmail(order.getUser().getEmail(), "Confirmation d'arrivée de la commande",
                        "Cher " + order.getUser().getUsername() + ",<br><br>"
                                + "Nous vous informons que votre commande a été livrée avec succès.<br><br>"
                                + "Merci de votre confiance. Vous pouvez confirmer l'arrivée de votre commande en suivant ce lien : <a href=\"" + confirmationLink + "\">Confirmer l'arrivée de la commande</a>");
            } catch (MessagingException e) {
                // Gérer l'erreur d'envoi d'email
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            return ResponseEntity.ok().body(order);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

// Dans le service OrderService

    public ResponseEntity<Orderr> confirmOrderDelivery(String orderCode) {
        Orderr order = orderRepository.findByCodeOrder(orderCode);
        if (order != null) {
            order.setConfirmedByClient(true);
            if (order.isConfirmedByDeliveryMan()) {
                order.setStatus(OrderStatus.DELIVERED);
            }
            orderRepository.save(order);
            return ResponseEntity.ok().body(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
