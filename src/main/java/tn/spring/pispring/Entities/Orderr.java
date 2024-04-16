package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    public Date dateOrder;
    public OrderStatus status;
    public float costOrder;
    public String codeOrder; // Champ pour le code de commande

    private String codeQR;

    @OneToMany(mappedBy = "orderr",cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    public List<OrderItem> orderItems;


    @ManyToOne

    User user;



    @OneToOne(mappedBy = "orderr")
    @ToString.Exclude
    @JsonIgnore
    private Delivery delivery;



}

