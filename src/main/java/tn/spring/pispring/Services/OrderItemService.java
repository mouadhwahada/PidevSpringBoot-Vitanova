package tn.spring.pispring.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.*;
import tn.spring.pispring.Interfaces.OrderItemInterface;
import tn.spring.pispring.Repositories.OrderItemRepository;
import tn.spring.pispring.Repositories.OrderRepository;
import tn.spring.pispring.Repositories.ProductRepository;
import tn.spring.pispring.Repositories.UserRepo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    OrderRepository orderRepository;

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





    public List<OrderItem> retrieveOrderItemsByUserId(Long iduser) {
        Optional<User> optionalUser = userRepo.findById(iduser);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("L'utilisateur avec l'ID " + iduser + " n'existe pas.");
        }
        User user = optionalUser.get();

        return orderItemRepository.findByUserIdAndPaidFalse(iduser);
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
        return null;
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





        // Enregistrez l'OrderItem dans la base de données
        return orderItemRepository.save(orderItem);
    }


    @Override
    public OrderItem addOrderItem1(int productId) {
        return null;
    }





    public OrderItem addOrderItemmm(int productId, Long iduser) {
        // Récupérer le produit correspondant à l'ID
        Product product = productRepository.findById(productId).orElse(null);

        // Vérifier si le produit existe
        if (product == null) {
            throw new IllegalArgumentException("Le produit avec l'ID " + productId + " n'existe pas.");
        }

        // Récupérer l'utilisateur correspondant à l'ID
        Optional<User> optionalUser = userRepo.findById(iduser);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("L'utilisateur avec l'ID " + iduser + " n'existe pas.");
        }
        User user = optionalUser.get();

        // Créer un nouvel OrderItem avec le produit et l'utilisateur
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setUser(user);

        // Enregistrer l'OrderItem dans la base de données
        return orderItemRepository.save(orderItem);
    }





    public List<Object[]> retrieveOrderItemsWithProducts() {
        return orderItemRepository.findAllOrderItemsWithProductNameAndPriceAndPaidFalse();
    }




    public boolean checkIfProductExistsInCart(int productId, Long userId) {
        // Récupérer la liste des OrderItems non payés correspondant au produit et à l'utilisateur
        List<OrderItem> existingOrderItems = orderItemRepository.findByUserIdAndPaidFalse(userId);

        // Parcourir la liste des OrderItems
        for (OrderItem existingOrderItem : existingOrderItems) {
            // Vérifier si l'OrderItem correspond au produit recherché
            if (existingOrderItem.getProduct().getIdProduct() == productId) {
                // Si un OrderItem correspondant est trouvé, retourner true
                return true;
            }
        }
        // Aucun OrderItem correspondant n'a été trouvé
        return false;
    }



    public OrderItem addOrderItemmmmm(int productId, Long iduser) {
        // Récupérer le produit correspondant à l'ID
        Product product = productRepository.findById(productId).orElse(null);

        // Vérifier si le produit existe
        if (product == null) {
            throw new IllegalArgumentException("Le produit avec l'ID " + productId + " n'existe pas.");
        }

        // Récupérer l'utilisateur correspondant à l'ID
        Optional<User> optionalUser = userRepo.findById(iduser);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("L'utilisateur avec l'ID " + iduser + " n'existe pas.");
        }
        User user = optionalUser.get();

        // Récupérer tous les OrderItems non payés de l'utilisateur
        List<OrderItem> existingOrderItems = orderItemRepository.findByUserIdAndPaidFalse(iduser);

        // Vérifier si le produit existe déjà dans le panier de l'utilisateur
        for (OrderItem existingOrderItem : existingOrderItems) {
            if (existingOrderItem.getProduct().getIdProduct() == productId) {
                throw new IllegalStateException("Le produit avec l'ID " + productId + " existe déjà dans le panier de l'utilisateur.");
            }
        }

        // Vérifier si l'utilisateur a déjà un ordre en cours
        Orderr existingOrder = orderRepository.findByUserAndStatus(user, OrderStatus.PROCESSING);

        // Si aucun ordre en cours n'existe, créer un nouvel ordre
        if (existingOrder == null) {
            Orderr newOrder = new Orderr();
            newOrder.setUser(user);
            newOrder.setDateOrder(Date.valueOf(LocalDate.now()));
            newOrder.setStatus(OrderStatus.PROCESSING);
            // Enregistrer le nouvel ordre dans la base de données
            newOrder = orderRepository.save(newOrder);

            // Créer un nouvel OrderItem avec le produit et l'ordre
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setUser(user);
            orderItem.setOrderr(newOrder);

            // Enregistrer l'OrderItem dans la base de données
            return orderItemRepository.save(orderItem);
        } else {
            // Si un ordre en cours existe, ajouter simplement l'élément de commande à cet ordre
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setUser(user);
            orderItem.setOrderr(existingOrder);


            // Enregistrer l'OrderItem dans la base de données
            return orderItemRepository.save(orderItem);
        }
    }


    ////INCREMENT AND DECREMENT

        public void incrementQuantity (int idOrderItem){
            OrderItem orderItem = orderItemRepository.findById(idOrderItem)
                    .orElseThrow(() -> new RuntimeException("Order item not found"));

            orderItem.setQuantity(orderItem.getQuantity() + 1);
            orderItemRepository.save(orderItem);
        }

        // Décrémenter la quantité de l'article de commande
        public void decrementQuantity(int idOrderItem) {
            OrderItem orderItem = orderItemRepository.findById(idOrderItem)
                    .orElseThrow(() -> new RuntimeException("Order item not found"));

            if (orderItem.getQuantity() > 1) {
                orderItem.setQuantity(orderItem.getQuantity() - 1);
                orderItemRepository.save(orderItem);
            }
        }









}