package tn.spring.pispring.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.OrderItem;
import tn.spring.pispring.Entities.Product;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaces.OrderItemInterface;
import tn.spring.pispring.Repositories.OrderItemRepository;
import tn.spring.pispring.Repositories.ProductRepository;
import tn.spring.pispring.Repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class OrderItemService implements OrderItemInterface {
    private List<Product> products = new ArrayList<>();

    @Autowired

    OrderItemRepository orderItemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired

    UserRepo userRepo;
    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> retrieveOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);

    }

    @Override
    public OrderItem updateOrderItem(int id, OrderItem orderItem) {
        Optional<OrderItem> existingOrderItem = orderItemRepository.findById(id);
        if (existingOrderItem.isPresent()) {
            orderItem.setIdOrderItem(id);
            return orderItemRepository.save(orderItem);
        } else {
            return null;
        }

    }

    @Override
    public OrderItem retrieveOrderItem(int id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public void removeOrderItem(int id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem addOrderItemForStaticUser(OrderItem orderItem) {
        Optional<User> optionalUser = userRepo.findById(1L); // Supposons que l'ID de l'utilisateur statique soit 1
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Associez l'utilisateur à l'élément de commande
            orderItem.setUser(user);

            // Enregistrez l'élément de commande
            return orderItemRepository.save(orderItem);
        } else {
            // Gérez l'erreur si l'utilisateur n'est pas trouvé
            throw new RuntimeException("Utilisateur avec l'ID 1 non trouvé.");
        }
    }


    @Override
    public OrderItem addOrderItem(int productId) {
        // Récupérez le produit correspondant à l'ID
        Product product = productRepository.findById(productId).orElse(null);

        // Vérifiez si le produit existe
        if (product == null) {
            // Gérez le cas où le produit n'existe pas
            throw new IllegalArgumentException("Le produit avec l'ID " + productId + " n'existe pas.");
        }

        // Créez un nouvel OrderItem avec le produit
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);

        // Enregistrez l'OrderItem dans la base de données
        return orderItemRepository.save(orderItem);
    }


    public List<Object[]> retrieveOrderItemsWithProducts() {
        return orderItemRepository.findAllOrderItemsWithProductNameAndPrice();
    }
}