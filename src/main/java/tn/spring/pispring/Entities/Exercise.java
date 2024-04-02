package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exercise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Exercice;
    private String namexercise;
    private Integer sets;
    private Integer reps ;
    private Integer repo ;
    private Integer duration;
    private String image;

    @ManyToOne
    @JoinColumn(name="exercise_day_id")
    private ExerciseDay exerciseDay;
}
