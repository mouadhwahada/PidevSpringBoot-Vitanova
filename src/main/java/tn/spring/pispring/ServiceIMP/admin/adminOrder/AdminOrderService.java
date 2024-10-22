package tn.spring.pispring.ServiceIMP.admin.adminOrder;

import tn.spring.pispring.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {

    List<OrderDto> getAllPlaceOrders();
    OrderDto changerOrderStatus(Long orderId,String status);
}
