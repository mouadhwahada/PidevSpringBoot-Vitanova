package tn.spring.pispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.*;
import tn.spring.pispring.Interfaces.OrderrInterface;
import tn.spring.pispring.Repositories.OrderRepository;
import tn.spring.pispring.Repositories.PaymentRepository;
import tn.spring.pispring.Repositories.UserRepo;

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


}
