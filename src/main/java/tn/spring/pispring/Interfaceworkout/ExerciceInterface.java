package tn.spring.pispring.Interfaceworkout;

import org.hibernate.sql.Update;
import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.Workoutprogram;

import java.util.List;

public interface ExerciceInterface {
    public Exercise creatExercice(Exercise exercise);
    public  Exercise Updatexrercice(Exercise exercise);
  public void   deleteExercice(Integer id_Exercice);
    public List<Exercise> getAllExercice();
    public Exercise getExerciceById(Integer id_Exercice);
    void addExerciseToDay(Exercise exercise,Integer exerciseDayId);
    List<Exercise> getExercisesByExcerciseDayId(Integer id);

}
