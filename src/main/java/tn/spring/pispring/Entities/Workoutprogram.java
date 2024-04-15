package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "workoutprograms")

    private List<Exercise> exercises;
    @OneToMany(mappedBy = "workoutprogramss")
    @ToString.Exclude
    @JsonIgnore
    private  List<FollowedProgram> followedPrograms;
}
