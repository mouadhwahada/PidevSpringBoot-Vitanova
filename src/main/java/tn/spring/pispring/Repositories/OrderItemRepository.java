package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.spring.pispring.Entities.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    @Query("SELECT oi, p.name, p.price FROM OrderItem oi JOIN oi.product p")
    List<Object[]> findAllOrderItemsWithProductNameAndPrice();
}
