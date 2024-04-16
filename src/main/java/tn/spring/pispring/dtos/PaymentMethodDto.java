package tn.spring.pispring.dtos;

import lombok.Data;

@Data
public class PaymentMethodDto {
    private String type;
    private CardDto card;
}
