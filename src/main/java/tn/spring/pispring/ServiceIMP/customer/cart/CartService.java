package tn.spring.pispring.ServiceIMP.customer.cart;

import org.springframework.http.ResponseEntity;
import tn.spring.pispring.dto.AddAbonnementInCartDto;
import tn.spring.pispring.dto.OrderDto;
import tn.spring.pispring.dto.PlaceOrderDto;

import java.util.List;

public interface CartService {

    ResponseEntity<?> addAbonnmentToCart(AddAbonnementInCartDto addAbonnementInCartDto);

    OrderDto getCartByUserId(Long userId);

    OrderDto applyCoupon(Long userId, String code);

    OrderDto increaseAbonnementQuantity(AddAbonnementInCartDto addAbonnementInCartDto);

    OrderDto decreaseAbonnementQuantity(AddAbonnementInCartDto addAbonnementInCartDto);

    OrderDto PlaceOrder(PlaceOrderDto placeOrderDto);
    List<OrderDto> getMyPlacedOrders(Long userId);
}
