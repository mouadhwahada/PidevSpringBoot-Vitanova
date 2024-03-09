package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.OrderItem;
import tn.spring.pispring.Services.OrderItemService;

import java.util.List;

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







}
