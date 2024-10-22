package tn.spring.pispring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Order;
import tn.spring.pispring.dto.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);


}
