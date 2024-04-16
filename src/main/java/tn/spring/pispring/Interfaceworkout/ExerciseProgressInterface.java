package tn.spring.pispring.Interfaceworkout;

import tn.spring.pispring.Entities.ExerciseDay;
import tn.spring.pispring.Entities.ExerciseProgress;

import java.util.List;

public interface ExerciseProgressInterface {
    ExerciseProgress creatExerciseProgress(ExerciseProgress exerciseProgress);
    ExerciseProgress UpdatExerciseProgress(ExerciseProgress exerciseProgress);
    void deleteExerciseProgress(Integer id_ExerciseProgress);
    List<ExerciseProgress> getAllExerciseProgress();
    ExerciseProgress getExerciseProgressById(Integer exerciseProgressId);
    List<ExerciseProgress> findByUserId(Long userId);
    void markExerciseAsCompleted(Integer exerciseProgressId);
    ExerciseProgress addExerciseProgressAndAssignedToUserAndExercise(ExerciseProgress exerciseProgress,Long userId,Integer exerciseId);
}
