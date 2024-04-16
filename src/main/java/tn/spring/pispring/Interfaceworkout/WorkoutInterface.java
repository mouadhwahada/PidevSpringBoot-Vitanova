package tn.spring.pispring.Interfaceworkout;

import tn.spring.pispring.Entities.Workoutprogram;

import java.util.List;

public interface WorkoutInterface {
    public Workoutprogram creatWorkoutprogramm (Workoutprogram w);
   public   void   deleteprogram (Integer id_workout);
    public   Workoutprogram updateWorkoutprogram (Workoutprogram w);
    public  List<Workoutprogram> getAllWorkoutprogramm();
   public Workoutprogram getWorkoutprogramById(Integer id_workout);
    public void deleteprogramByName(String name);
    void assignUserToWorkout(Integer workoutProgramId,Long userId);
    List<Workoutprogram> getWorkoutProgramByNonUserId(Long idUser);
    List<Workoutprogram> getWorkoutProgramByUserId(Long idUser);




}
