package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int deliveryId;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    private String deliveryAddress;
    private String deliveryPostalCode;
    private String deliveryCity;


    @OneToOne
    private Orderr orderr;


    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    User user;
}
