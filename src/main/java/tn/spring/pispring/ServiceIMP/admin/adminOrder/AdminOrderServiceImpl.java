package tn.spring.pispring.ServiceIMP.admin.adminOrder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Order;
import tn.spring.pispring.dto.OrderDto;
import tn.spring.pispring.dto.OrderStatus;
import tn.spring.pispring.repo.OrderRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService{

    private final OrderRepository orderRepository;

    public List<OrderDto> getAllPlaceOrders(){
        List<Order> orderList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Accepted, OrderStatus.Refused));

        return  orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    public OrderDto changerOrderStatus(Long orderId,String status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            if (Objects.equals(status, "Accepted")) {
                order.setOrderStatus(OrderStatus.Accepted);

            } else if (Objects.equals(status, "Refused")) {
                order.setOrderStatus(OrderStatus.Refused);
            }
            return orderRepository.save(order).getOrderDto();

        }
        return null;
    }
}
