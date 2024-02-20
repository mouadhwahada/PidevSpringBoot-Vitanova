package tn.spring.pispring.Entities;


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
    private Integer id;
    private String weightGoal;
    private String steps;
    private String duration;
    private String height;
    private String Neck;
    private String Waist;
    @ManyToOne(cascade = CascadeType.ALL)
    Workoutprogram  workoutprogramss ;

    @ManyToOne
    User userworkout;
}
