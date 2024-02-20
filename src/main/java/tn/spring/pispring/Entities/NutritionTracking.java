package tn.spring.pispring.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class NutritionTracking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNutTrack;
    private LocalDate dateNut;
    private Long total_calories;

    @ManyToOne
    private NutritionalGoal nutritiongoal;

    @OneToMany(mappedBy = "nuttrack")
    private List<Food> consumedFoods;

}