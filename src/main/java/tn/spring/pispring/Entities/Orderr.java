package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orderr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idOrder;
    @Temporal(TemporalType.DATE)
    private Date dateOrder;
    public OrderStatus status;
    public float costOrder;

    @OneToMany(mappedBy = "orderr")
    @ToString.Exclude
    @JsonIgnore
    public List<OrderItem> orderItems;


    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    User user;


    @ToString.Exclude
    @JsonIgnore
    @OneToOne(mappedBy = "orderr")
    private Delivery delivery;
}
