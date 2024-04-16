package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long idNutTrack;
    private String dateNut;
    private long total_calories;
    @Transient
    private int quantity;

    @ManyToOne
    private NutritionalGoal nutritiongoal;

    @OneToMany(mappedBy = "nuttrack")
    @JsonIgnore
    private List<Food> consumedFoods;




    public void setDateNut() {
        this.dateNut = LocalDate.now().toString();
    }

}