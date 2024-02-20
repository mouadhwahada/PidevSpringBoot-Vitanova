package tn.spring.pispring.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workoutprogram implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String duration ;
    @OneToMany(mappedBy = "workoutprograms",cascade = CascadeType.ALL)
    private List<Exercise> exercises;
    @OneToMany(mappedBy = "workoutprogramss",cascade = CascadeType.ALL)
    private  List<FollowedProgram> followedPrograms;
}
