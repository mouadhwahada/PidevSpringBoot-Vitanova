package tn.spring.pispring.Controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.spring.pispring.ServiceIMP.admin.adminOrder.AdminOrderService;
import tn.spring.pispring.dto.OrderDto;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminOrderController {

private final AdminOrderService adminOrderService;

@GetMapping("/placedOrders")
public ResponseEntity<List<OrderDto>> getAllPlacedOrders(){
    return ResponseEntity.ok(adminOrderService.getAllPlaceOrders());
}

@GetMapping("/order/{orderId}/{status}")
public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status){
    OrderDto orderDto=adminOrderService.changerOrderStatus(orderId,status);
    if (orderDto == null)
        return  new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
    return ResponseEntity.status(HttpStatus.OK).body(orderDto);
}
}
