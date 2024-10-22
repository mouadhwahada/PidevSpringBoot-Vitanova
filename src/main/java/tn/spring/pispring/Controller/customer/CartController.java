package tn.spring.pispring.Controller.customer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.ServiceIMP.customer.cart.CartService;
import tn.spring.pispring.dto.AddAbonnementInCartDto;
import tn.spring.pispring.dto.OrderDto;
import tn.spring.pispring.dto.PlaceOrderDto;
import tn.spring.pispring.exceptions.ValidationException;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public  ResponseEntity<?> addAbonnmentToCart(@RequestBody AddAbonnementInCartDto addAbonnementInCartDto){
        return cartService.addAbonnmentToCart(addAbonnementInCartDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
      OrderDto orderDto= cartService.getCartByUserId(userId);

      return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code){
        try {
            OrderDto orderDto=cartService.applyCoupon(userId,code);
            return ResponseEntity.ok(orderDto);
        }catch (ValidationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @PostMapping("/addition")
    public  ResponseEntity<OrderDto> increaseAbonnementQuantity(@RequestBody AddAbonnementInCartDto addAbonnementInCartDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseAbonnementQuantity(addAbonnementInCartDto));
    }

    @PostMapping("/deduction")
    public  ResponseEntity<OrderDto> decreaseAbonnementQuantity(@RequestBody AddAbonnementInCartDto addAbonnementInCartDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseAbonnementQuantity(addAbonnementInCartDto));
    }

    @PostMapping("/placeOrder")
    public  ResponseEntity<OrderDto> PlaceOrder(@RequestBody PlaceOrderDto placeOrderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.PlaceOrder(placeOrderDto));
    }

    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId){
        return  ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
    }
}
