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
public class Exercise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namexercise;
    private Integer stes;
    private Integer reps ;
    private String repo ;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    Workoutprogram  workoutprograms ;
}
