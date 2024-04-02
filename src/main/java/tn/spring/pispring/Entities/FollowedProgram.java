package tn.spring.pispring.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class FollowedProgram implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_FollowedProgram;
    private String weightGoal;
    private double currentWeight;
    private double waist;
    private double hip;
    private double chest;

    @JsonIgnore
    @ManyToOne
    User userworkout;
}
