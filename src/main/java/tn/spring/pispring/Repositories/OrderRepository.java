package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.spring.pispring.Entities.OrderStatus;
import tn.spring.pispring.Entities.Orderr;
import tn.spring.pispring.Entities.Product;
import tn.spring.pispring.Entities.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orderr,Integer> {

    boolean existsByCodeOrder(String codeOrder);

    Orderr findByUserAndStatus(User user, OrderStatus status);
    Orderr findByUser(User user);

     long count() ;
List<Orderr> findByStatus(OrderStatus status);

    long countByStatus(OrderStatus status);


Orderr findByCodeOrder(String CodeOrder);
    List<Orderr> findByCodeOrderContainingIgnoreCase(String codeOrder);


    @Query("SELECT o FROM Orderr o LEFT JOIN FETCH o.deliveryMan")
    List<Orderr> findAllWithDeliveryMan();


    @Query("SELECT o.deliveryMan FROM Orderr o WHERE o.idOrder = :orderId")
    User findDeliveryManByOrderId(int orderId);

    List<Orderr> findByDeliveryMan(User user);

}
