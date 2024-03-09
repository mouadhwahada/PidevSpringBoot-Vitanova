package tn.spring.pispring.Entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int idProduct;
    public String name;
    public Float price;
    public String description;
    public int stockQuantity;
    @Enumerated(EnumType.STRING)
    public TypeProduit type;
    public boolean isfavourite=false;
    public String image;
    // @Temporal(TemporalType.TIMESTAMP)
    //public Date dateAdded;

    @OneToMany(mappedBy = "product")

    @JsonIgnore
    @ToString.Exclude
    private List<OrderItem> orderItems;


}
