package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private int duration;
  private int height;
  private int weight;
    private String goal;
    private int weight_goal;
    @Transient
    private long daily_calorie_goal;


    @OneToOne
    @JoinColumn(name = "user_id") // Nom de la colonne dans la table NutritionalGoal qui fait référence à l'ID de l'utilisateur
    private User user;

    @OneToMany(mappedBy = "nutritiongoal")
    @JsonIgnore
    private List<NutritionTracking> NuttrackingList;
}
