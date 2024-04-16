package tn.spring.pispring.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseProgress {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idExerciseProgress;
    @ManyToOne
    @JoinColumn(name="exercise_id")
    private Exercise exercise;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private LocalDate completionDate;
    private Boolean completed;
}
