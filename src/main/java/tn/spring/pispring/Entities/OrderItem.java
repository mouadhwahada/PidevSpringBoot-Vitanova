package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    public int idOrderItem;
    public int quantity;
    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    public List<Product> products;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    public Orderr orderr;

}
