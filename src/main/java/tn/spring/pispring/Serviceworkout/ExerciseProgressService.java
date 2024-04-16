package tn.spring.pispring.Serviceworkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.ExerciseProgress;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaceworkout.ExerciseProgressInterface;
import tn.spring.pispring.Repositories.UserRepo;
import tn.spring.pispring.repositoriesworkout.Exercicerepository;
import tn.spring.pispring.repositoriesworkout.ExerciseProgressRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseProgressService implements ExerciseProgressInterface {

    @Autowired
    private ExerciseProgressRepository exerciseProgressRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Exercicerepository exercicerepository;

    @Override
    public ExerciseProgress creatExerciseProgress(ExerciseProgress exerciseProgress) {
        return exerciseProgressRepository.save(exerciseProgress);
    }

    @Override
    public ExerciseProgress UpdatExerciseProgress(ExerciseProgress exerciseProgress) {
        return exerciseProgressRepository.save(exerciseProgress);
    }

    @Override
    public void deleteExerciseProgress(Integer id_ExerciseProgress) {
        exerciseProgressRepository.deleteById(id_ExerciseProgress);
    }

    @Override
    public List<ExerciseProgress> getAllExerciseProgress() {
        return exerciseProgressRepository.findAll();
    }

    @Override
    public ExerciseProgress getExerciseProgressById(Integer exerciseProgressId) {
        return exerciseProgressRepository.findById(exerciseProgressId).orElse(null);
    }

    @Override
    public List<ExerciseProgress> findByUserId(Long userId) {
        List<ExerciseProgress> list=new ArrayList<>();
        for(ExerciseProgress exerciseProgress:exerciseProgressRepository.findAll()){
            if(exerciseProgress.getUser().getId().equals(userId)){
                list.add(exerciseProgress);
            }
        }
        return list;
    }

    @Override
    public void markExerciseAsCompleted(Integer exerciseProgressId) {
        ExerciseProgress exerciseProgress=exerciseProgressRepository.findById(exerciseProgressId).orElse(null);
        if(exerciseProgress!=null){
            exerciseProgress.setCompleted(true);
            exerciseProgress.setCompletionDate(LocalDate.now());
            exerciseProgressRepository.save(exerciseProgress);
        }
    }



    @Override
    public ExerciseProgress addExerciseProgressAndAssignedToUserAndExercise(ExerciseProgress exerciseProgress, Long userId, Integer exerciseId) {
        User user=userRepo.findById(userId).orElse(null);
        Exercise exercise=exercicerepository.findById(exerciseId).orElse(null);
        if(user!=null && exercise!=null){
            exerciseProgress.setUser(user);
            exerciseProgress.setExercise(exercise);
            return exerciseProgressRepository.save(exerciseProgress);
        }
        return null;
    }
    public void markExerciseAsCompletedByUserAndExercise(Long userId, Integer exerciseId) {
        List<ExerciseProgress> progressList = exerciseProgressRepository.findAll();
        progressList.stream()
                .filter(progress -> progress.getUser().getId().equals(userId) &&
                        progress.getExercise().getId_Exercice().equals(exerciseId) &&
                        !progress.getCompleted())
                .findFirst()
                .ifPresent(exerciseProgress -> {
                    exerciseProgress.setCompleted(true);
                    exerciseProgress.setCompletionDate(LocalDate.now());
                    exerciseProgressRepository.save(exerciseProgress);
                });
    }

    public List<Exercise> getCompletedExercisesByUserAndWorkout(Long userId, Integer workoutId) {
        List<ExerciseProgress> progressList = exerciseProgressRepository.findAll();
        return progressList.stream()
                .filter(progress -> progress.getUser().getId().equals(userId) &&
                        progress.getExercise().getExerciseDay().getWorkoutProgram().getId_workout().equals(workoutId) &&
                        progress.getCompleted())
                .map(ExerciseProgress::getExercise)
                .collect(Collectors.toList());
    }
}
