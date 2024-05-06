package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int idOrderItem;
    public int quantity;

    @Column(name = "paid", nullable = false)
    private boolean paid = false;

    @ManyToOne

    public Product product;

    @ManyToOne

    public Orderr orderr;



    @ManyToOne

    public User user;

}
