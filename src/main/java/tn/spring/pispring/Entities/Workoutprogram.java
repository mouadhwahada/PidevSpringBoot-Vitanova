package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workoutprogram implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_workout;
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

    private Integer duration ;
    private String targetGroup;
    private String category;
    @ManyToMany(mappedBy = "workoutPrograms")
    private Set<User> user;
    @JsonIgnore
    @OneToMany(mappedBy = "workoutProgram",cascade = CascadeType.ALL)
    private List<ExerciseDay> exercisesPerDay;

}
