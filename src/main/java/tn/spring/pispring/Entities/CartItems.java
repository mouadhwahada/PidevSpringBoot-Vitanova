package tn.spring.pispring.Entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tn.spring.pispring.dto.CartItemsDto;

import javax.persistence.*;

@Entity
@Data

public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    private Long quantity;

    @ManyToOne(fetch =FetchType.LAZY,optional = false)
    @JoinColumn(name ="abonnement_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Abonnement abonnement;


    @ManyToOne(fetch =FetchType.LAZY,optional = false)
    @JoinColumn(name ="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="order_id")
    private Order order;

    public CartItemsDto getCartDto(){

        CartItemsDto cartItemsDto = new CartItemsDto();
        cartItemsDto.setId(id);
        cartItemsDto.setPrice(price);
        cartItemsDto.setAbonnementId(abonnement.getId());
        cartItemsDto.setQuantity(quantity);
        cartItemsDto.setUserId(user.getId());
        cartItemsDto.setAbonnementName(abonnement.getName());
        cartItemsDto.setReturnedImg(abonnement.getImg());

        return cartItemsDto;

    }


}
