package tn.spring.pispring.dto;

import lombok.Data;
import tn.spring.pispring.Entities.CartItems;
import tn.spring.pispring.Entities.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data

public class OrderDto {


    private Long id;

    private String orderDescription;

    private Date date;
    //AFter applying the coupon code
    private Long amount;

    private String address;

    private String payment;

    private OrderStatus orderStatus;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;

    private String userName;

    private List<CartItemsDto> cartItems;

    private String couponName;
}
