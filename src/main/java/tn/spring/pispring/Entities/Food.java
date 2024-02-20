package tn.spring.pispring.Entities;


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
    private Long idFood;
    private String namefood;
    private Long calories_per_serving ;
    private Long  protein_per_serving;
    private Long  carbohydrates_per_Serving;
    private Long  fat_per_Serving;
    private Long  fiber_per_Serving;
    private Long  vitamins_per_Serving;
    private Long  minerals_per_Serving;

    @ManyToOne
    private NutritionTracking nuttrack;
}
