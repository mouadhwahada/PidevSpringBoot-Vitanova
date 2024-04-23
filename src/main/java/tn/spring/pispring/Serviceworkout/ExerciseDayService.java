package tn.spring.pispring.Serviceworkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.ExerciseDay;
import tn.spring.pispring.Entities.Workoutprogram;
import tn.spring.pispring.Interfaceworkout.ExerciseDayInterface;
import tn.spring.pispring.repositoriesworkout.ExerciseDayRepository;
import tn.spring.pispring.repositoriesworkout.Workoutrepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExerciseDayService implements ExerciseDayInterface {
    @Autowired
    private ExerciseDayRepository exerciseDayRepository;
    @Autowired
    private Workoutrepository workoutrepository;
    @Override
    public ExerciseDay creatExerciceDay(ExerciseDay exerciseday) {
        return exerciseDayRepository.save(exerciseday);
    }

    @Override
    public ExerciseDay UpdatexrerciceDay(ExerciseDay exerciseday) {
        return exerciseDayRepository.save(exerciseday);
    }

    @Override
    public void deleteExerciceDay(Integer id_Exerciceday) {
        exerciseDayRepository.deleteById(id_Exerciceday);
    }

    @Override
    public List<ExerciseDay> getAllExerciseDay() {
        return exerciseDayRepository.findAll();
    }

    @Override
    public ExerciseDay getExerciseDayById(Integer exerciseDayId) {
        return exerciseDayRepository.findById(exerciseDayId).orElse(null);
    }

    @Override
    public ExerciseDay addExerciceDayToWorkout(ExerciseDay exerciseday, Integer id_workout) {
        Workoutprogram workoutprogram=workoutrepository.findById(id_workout).orElse(null);
        if(workoutprogram!=null){
            exerciseday.setWorkoutProgram(workoutprogram);
            return exerciseDayRepository.save(exerciseday);
        }
        return null;
    }

    @Override
    public void completeDay(Integer exercisedayId) {
        ExerciseDay exerciseDay=exerciseDayRepository.findById(exercisedayId).orElse(null);
        if(exerciseDay!=null){
            exerciseDay.setCompleted(true);
            exerciseDayRepository.save(exerciseDay);
        }
    }
    public List<ExerciseDay> getExerciseDaysByWorkoutId(Integer workoutId) {
        List<ExerciseDay> list=new ArrayList<>();
        for(ExerciseDay exerciseDay:exerciseDayRepository.findAll()){
            if(exerciseDay.getWorkoutProgram().getId_workout().equals(workoutId)){
                list.add(exerciseDay);
            }
        }
        return list;
    }

}
