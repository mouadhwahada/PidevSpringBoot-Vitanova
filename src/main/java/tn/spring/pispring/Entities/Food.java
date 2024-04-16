package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFood;
    private String namefood;
    private long calories_per_serving ;
    private long  protein_per_serving;
    private long  carbohydrates_per_Serving;
    private long  fat_per_Serving;
    private long  fiber_per_Serving;
    private String vitamins_per_Serving;
    private long  minerals_per_Serving;
  @JsonIgnore
    @ManyToOne
    private NutritionTracking nuttrack;
}
