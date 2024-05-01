package tn.spring.pispring.ServiceIMP.customer.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.*;
import tn.spring.pispring.dto.*;
import tn.spring.pispring.exceptions.ValidationException;
import tn.spring.pispring.repo.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements  CartService {


    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    public ResponseEntity<?> addAbonnmentToCart(AddAbonnementInCartDto addAbonnementInCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addAbonnementInCartDto.getUserId(), OrderStatus.Pending);

        Optional<CartItems> optionalCartItems = cartItemsRepository.findByAbonnementIdAndOrderIdAndUserId
                (addAbonnementInCartDto.getAbonnementId(), activeOrder.getId(), addAbonnementInCartDto.getUserId());

        if (optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<Abonnement> optionalAbonnement = abonnementRepository.findById(addAbonnementInCartDto.getAbonnementId());
            Optional<User> optionalUser = userRepository.findById(addAbonnementInCartDto.getUserId());

            if (optionalAbonnement.isPresent() && optionalUser.isPresent()) {
                CartItems cart = new CartItems();
                cart.setAbonnement(optionalAbonnement.get());
                cart.setPrice(optionalAbonnement.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or abonnement not found");
            }
        }


    }

    public OrderDto getCartByUserId(Long userId) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);
        if (activeOrder.getCoupon() != null) {
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }
        return orderDto;
    }

    public OrderDto applyCoupon(Long userId, String code) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new ValidationException("coupon not found !!!"));


        if (couponIsExpired(coupon)) {
            throw new ValidationException("Coupon has expired");
        }

        double discountAmount = ((coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount() - discountAmount;

        activeOrder.setAmount((long) netAmount);
        activeOrder.setDiscount((long) discountAmount);
        activeOrder.setCoupon(coupon);

        orderRepository.save(activeOrder);
        return activeOrder.getOrderDto();
    }

    private boolean couponIsExpired(Coupon coupon) {
        Date currentdate = new Date();
        Date expirationDate = coupon.getExpirationDate();

        return expirationDate != null && currentdate.after(expirationDate);
    }

    public OrderDto increaseAbonnementQuantity(AddAbonnementInCartDto addAbonnementInCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addAbonnementInCartDto.getUserId(), OrderStatus.Pending);
        Optional<Abonnement> optionalAbonnement = abonnementRepository.findById(addAbonnementInCartDto.getAbonnementId());

        Optional<CartItems> optionalCartItems = cartItemsRepository.findByAbonnementIdAndOrderIdAndUserId(
                addAbonnementInCartDto.getAbonnementId(), activeOrder.getId(), addAbonnementInCartDto.getUserId()
        );
        if (optionalAbonnement.isPresent() && optionalCartItems.isPresent()) {
            CartItems cartItems = optionalCartItems.get();
            Abonnement abonnement = optionalAbonnement.get();

            activeOrder.setAmount(activeOrder.getAmount() + abonnement.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + abonnement.getPrice());

            cartItems.setQuantity(cartItems.getQuantity() + 1);

            if (activeOrder.getCoupon() != null) {
                double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);
            }

            cartItemsRepository.save(cartItems);
            return activeOrder.getOrderDto();
        }
        return null;
    }

    public OrderDto decreaseAbonnementQuantity(AddAbonnementInCartDto addAbonnementInCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addAbonnementInCartDto.getUserId(), OrderStatus.Pending);
        Optional<Abonnement> optionalAbonnement = abonnementRepository.findById(addAbonnementInCartDto.getAbonnementId());

        Optional<CartItems> optionalCartItems = cartItemsRepository.findByAbonnementIdAndOrderIdAndUserId(
                addAbonnementInCartDto.getAbonnementId(), activeOrder.getId(), addAbonnementInCartDto.getUserId()
        );
        if (optionalAbonnement.isPresent() && optionalCartItems.isPresent()) {
            CartItems cartItems = optionalCartItems.get();
            Abonnement abonnement = optionalAbonnement.get();

            activeOrder.setAmount(activeOrder.getAmount() - abonnement.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - abonnement.getPrice());

            cartItems.setQuantity(cartItems.getQuantity() - 1);

            if (activeOrder.getCoupon() != null) {
                double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);
            }

            cartItemsRepository.save(cartItems);
            return activeOrder.getOrderDto();
        }
        return null;
    }

    public OrderDto PlaceOrder(PlaceOrderDto placeOrderDto){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);

        Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());
        if(optionalUser.isPresent()){
            activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
        activeOrder.setAddress(placeOrderDto.getAddress());
        activeOrder.setDate(new Date());
        activeOrder.setOrderStatus(OrderStatus.Placed);
        activeOrder.setTrackingId(UUID.randomUUID());

        orderRepository.save(activeOrder);

            Order order= new Order();
            order.setAmount(0L);
            order.setTotalAmount(0L);
            order.setDiscount(0L);
            order.setUser(optionalUser.get());
            order.setOrderStatus(OrderStatus.Pending);
            orderRepository.save(order);

            return activeOrder.getOrderDto();
        }
        return null;

    }

    public List<OrderDto> getMyPlacedOrders(Long userId){
        return orderRepository.findByUserIdAndOrderStatusIn(userId,List.of(OrderStatus.Placed,OrderStatus.Delivered,OrderStatus.Shipped)).stream().map(Order::getOrderDto).collect(Collectors.toList());

    }
}
