package tn.spring.pispring.Serviceworkout;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.ExerciseDay;
import tn.spring.pispring.Entities.ExerciseProgress;
import tn.spring.pispring.Entities.Workoutprogram;
import tn.spring.pispring.Interfaceworkout.ExerciceInterface;
import tn.spring.pispring.repositoriesworkout.Exercicerepository;
import tn.spring.pispring.repositoriesworkout.ExerciseDayRepository;
import tn.spring.pispring.repositoriesworkout.ExerciseProgressRepository;
import tn.spring.pispring.repositoriesworkout.Workoutrepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExerciceService implements ExerciceInterface {
Exercicerepository exercicerepository;
ExerciseDayRepository exerciseDayRepository;
Workoutrepository workoutrepository;
    private ExerciseProgressRepository exerciseProgressRepository;
    @Override
    public Exercise creatExercice(Exercise exercise) {

         return exercicerepository.save(exercise);
    }


    @Override
    public Exercise Updatexrercice(Exercise exercise) {

        return exercicerepository.save(exercise);

    }

    @Override
    public void deleteExercice(Integer id_Exercice) {

        exercicerepository.deleteById(id_Exercice);
    }

    @Override
    public List<Exercise> getAllExercice() {

        return exercicerepository.findAll();
    }



    @Override
    public Exercise getExerciceById(Integer id_Exercice) {
        return  exercicerepository.findById(id_Exercice).orElse(null);
    }

    @Override
    public void addExerciseToDay(Exercise exercise, Integer exerciseDayId) {
        ExerciseDay exerciseDay = exerciseDayRepository.findById(exerciseDayId)
                .orElseThrow(() -> new EntityNotFoundException("ExerciseDay not found"));
        exercise.setExerciseDay(exerciseDay);
        exercicerepository.save(exercise);
    }


    public List<Exercise> getExercisesByExcerciseDayId(Integer id){
        List<Exercise> list=new ArrayList<>();
        for(Exercise exercise:exercicerepository.findAll()){
            if(exercise.getExerciseDay().getId().equals(id)){
                list.add(exercise);
            }
        }
        return list;
    }
    public boolean areAllExercisesCompletedForDay(Long userId, Integer exerciseDayId) {
        List<Exercise> exercises = getExercisesByExcerciseDayId(exerciseDayId);

        List<ExerciseProgress> progressList = findByUserId(userId);

        for (Exercise exercise : exercises) {
            boolean isCompleted = progressList.stream()
                    .anyMatch(progress -> progress.getExercise().getId_Exercice().equals(exercise.getId_Exercice()) && progress.getCompleted());

            if (!isCompleted) {
                return false;
            }
        }
        return true;
    }

    public List<ExerciseProgress> findByUserId(Long userId) {
        List<ExerciseProgress> list = new ArrayList<>();
        for (ExerciseProgress exerciseProgress : exerciseProgressRepository.findAll()) {
            if (exerciseProgress.getUser().getId().equals(userId)) {
                list.add(exerciseProgress);
            }
        }
        return list;
    }

}
