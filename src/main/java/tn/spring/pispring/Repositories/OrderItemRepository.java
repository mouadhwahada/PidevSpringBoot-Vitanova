package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.spring.pispring.Entities.OrderItem;
import tn.spring.pispring.Entities.Product;
import tn.spring.pispring.Entities.TypeProduit;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    @Query("SELECT oi, p.name, p.price FROM OrderItem oi JOIN oi.product p")
    List<Object[]> findAllOrderItemsWithProductNameAndPriceAndPaidFalse();
    List<OrderItem> findByUserIdAndPaidFalse(Long iduser);

    OrderItem findByUserIdAndProduct_IdProduct(Long userId, int productId);



}
