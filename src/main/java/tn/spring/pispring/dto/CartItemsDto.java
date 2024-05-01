package tn.spring.pispring.dto;


import lombok.Data;

@Data
public class CartItemsDto {


    private Long id;

    private Long price;

    private Long quantity;

    private Long abonnementId;

    private Long orderId;

    private String abonnementName;


    private byte [] returnedImg;

    private Long userId;

}
