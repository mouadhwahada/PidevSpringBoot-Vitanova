package tn.spring.pispring.Entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
    public String image;
    public float price;
    public String description;
    public int stockQuantity;
    @Enumerated(EnumType.STRING)
    public TypeProduit type;
    public boolean isfavourite;

}
