package tn.spring.pispring.Serviceworkout;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Entities.Workoutprogram;
import tn.spring.pispring.Interfaceworkout.WorkoutInterface;
import tn.spring.pispring.Repositories.UserRepo;
import tn.spring.pispring.repositoriesworkout.Workoutrepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class WorkoutService implements WorkoutInterface {
    private Workoutrepository workoutrepository;
    private UserRepo userRepo;

    @Override
    public Workoutprogram creatWorkoutprogramm(Workoutprogram w) {
        return workoutrepository.save(w);
    }

    @Override
    public void deleteprogram(Integer id_workout) {
         workoutrepository.deleteById(id_workout);
    }



    @Override
    public Workoutprogram updateWorkoutprogram( Workoutprogram w){

        return workoutrepository.save(w);
    }


    @Override
    public List<Workoutprogram> getAllWorkoutprogramm() {
        return workoutrepository.findAll();

    }

    @Override
    public Workoutprogram getWorkoutprogramById(Integer id_workout) {
    return  workoutrepository.findById(id_workout).orElse(null);

    }
    public void deleteprogramByName(String name) {
        workoutrepository.deleteByName(name);
    }

    @Override
    public void assignUserToWorkout(Integer workoutProgramId, Long userId) {
        Workoutprogram workoutprogram=workoutrepository.findById(workoutProgramId).orElse(null);
        User user=userRepo.findById(userId).orElse(null);
        if(user!=null && workoutprogram!=null){
            Set<Workoutprogram> workoutprogramSet=user.getWorkoutPrograms();
            workoutprogramSet.add(workoutprogram);
            user.setWorkoutPrograms(workoutprogramSet);
            userRepo.save(user);


        }
    }
    ///myworkouts
    public List<Workoutprogram> getWorkoutProgramByUserId(Long idUser) {
        User user=userRepo.findById(idUser).orElse(null);
        return user.getWorkoutPrograms().stream().collect(Collectors.toList());
    }
   ///workouts
    public List<Workoutprogram> getWorkoutProgramByNonUserId(Long idUser) {
        List<Workoutprogram> allWorkouts = workoutrepository.findAll();
        List<Workoutprogram> userWorkouts = getWorkoutProgramByUserId(idUser);

        return allWorkouts.stream()
                .filter(workout -> !userWorkouts.contains(workout))
                .collect(Collectors.toList());
    }



}





