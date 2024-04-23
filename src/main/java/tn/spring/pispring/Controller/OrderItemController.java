package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.OrderItem;
import tn.spring.pispring.Entities.Product;
import tn.spring.pispring.Services.OrderItemService;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin("*")

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/add")
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.addOrderItem(orderItem);
    }

    @GetMapping("/all")
    public List<OrderItem> retrieveOrderItems() {
        return orderItemService.retrieveOrderItems();
    }

    @PutMapping("/update")
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(orderItem);
    }

    @PutMapping("/update/{id}")
    public OrderItem updateOrderItem(@PathVariable int id, @RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(id, orderItem);
    }

    @GetMapping("/{id}")
    public OrderItem retrieveOrderItem(@PathVariable int id) {
        return orderItemService.retrieveOrderItem(id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeOrderItem(@PathVariable int id) {
        orderItemService.removeOrderItem(id);
    }


    @PostMapping("/orderitempradd")
    public OrderItem addOrderItem(@RequestParam("productId") int productId) {
        // Appelez le service pour ajouter le produit à l'élément de commande
        return orderItemService.addOrderItem(productId);
    }


    @GetMapping("/all-with-products")
    public List<Object[]> retrieveOrderItemsWithProducts() {
        return orderItemService.retrieveOrderItemsWithProducts();
    }


    @PostMapping("/orderItemss")
    public ResponseEntity<OrderItem> addOrderItemmm(
            @RequestParam("productId") int productId,
            @RequestParam("iduser") Long iduser) {
        OrderItem orderItem = orderItemService.addOrderItemmm(productId, iduser);
        return new ResponseEntity<>(orderItem, HttpStatus.CREATED);
    }



    ///2eme methode amelioré

    @PostMapping("/orderItems")
    public ResponseEntity<OrderItem> addOrderItemmmmm(
            @RequestParam("productId") int productId,
            @RequestParam("iduser") Long iduser) {
        OrderItem orderItem = orderItemService.addOrderItemmmmm(productId, iduser);
        return new ResponseEntity<>(orderItem, HttpStatus.CREATED);
    }


    public List<OrderItem> retrieveOrderItemsByUserId(Long iduser) {
        // Implémentez la logique pour récupérer les OrderItem associés à l'utilisateur spécifié
        return orderItemService.retrieveOrderItemsByUserId(iduser);
    }



// Endpoint pour incrémenter la quantité de l'article de commande
    @PutMapping("/items/{idOrderItem}/increment")
    public ResponseEntity<Void> incrementQuantity(@PathVariable int idOrderItem) {
        orderItemService.incrementQuantity(idOrderItem);
        return ResponseEntity.ok().build();
    }

    // Endpoint pour décrémenter la quantité de l'article de commande
    @PutMapping("/items/{idOrderItem}/decrement")
    public ResponseEntity<Void> decrementQuantity(@PathVariable int idOrderItem) {
        orderItemService.decrementQuantity(idOrderItem);
        return ResponseEntity.ok().build();
    }




















}