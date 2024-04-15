package tn.spring.pispring.Interfaceworkout;

import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.ExerciseDay;

import java.util.List;

public interface ExerciseDayInterface {
    ExerciseDay creatExerciceDay(ExerciseDay exerciseday);
    ExerciseDay UpdatexrerciceDay(ExerciseDay exerciseday);
    void deleteExerciceDay(Integer id_Exerciceday);
    List<ExerciseDay> getAllExerciseDay();
    ExerciseDay getExerciseDayById(Integer exerciseDayId);

    ExerciseDay addExerciceDayToWorkout(ExerciseDay exerciseday, Integer id_workout );
    void completeDay(Integer exercisedayId);
    List<ExerciseDay> getExerciseDaysByWorkoutId(Integer workoutId);

}
