package tn.spring.pispring.Entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;


    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @JsonIgnore

    private List<OrderItem> orderItems;


}
