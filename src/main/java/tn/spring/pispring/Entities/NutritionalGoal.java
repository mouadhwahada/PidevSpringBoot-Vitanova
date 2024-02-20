package tn.spring.pispring.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class NutritionalGoal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNGoal;

    private Long daily_calorie_goal;
    private Long daily_protein_goal;
    private Long daily_carbohydrates_goal;
    private String goal;
    private float weight_goal;
    private int Duration;

    @OneToMany(mappedBy = "nutritionalGoal")
    private List<User> userList;

    @OneToMany(mappedBy = "nutritiongoal")
    private List<NutritionTracking> NuttrackingList;
}
