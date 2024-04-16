package tn.spring.pispring.WorkoutController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Entities.Workoutprogram;
import tn.spring.pispring.Serviceworkout.WorkoutService;

import java.util.List;
@CrossOrigin("*")
@RestController

@AllArgsConstructor
public class WorkoutControlleur {
    WorkoutService serviceworkout;

    @PostMapping("/creatWorkoutprogramm")
    public Workoutprogram creatWorkoutprogramm(@RequestBody Workoutprogram w) {
        return serviceworkout.creatWorkoutprogramm(w);
    }

    @DeleteMapping("/deleteprogram/{id_workout}")
    public void deleteprogram(@PathVariable("id_workout") Integer id_workout) {
        serviceworkout.deleteprogram(id_workout);
    }




    @PutMapping("/updateWorkoutprogram")
    public Workoutprogram updateWorkoutprogram(@RequestBody Workoutprogram w) {
        return serviceworkout.updateWorkoutprogram(w);
    }

    @GetMapping("/getAllWorkoutprogramm")
    public List<Workoutprogram> getAllWorkoutprogramm() {
        return serviceworkout.getAllWorkoutprogramm();
    }

    @GetMapping("/getWorkoutprogramById/{id_workout}")
    public Workoutprogram getWorkoutprogramById(@PathVariable Integer id_workout) {
        return serviceworkout.getWorkoutprogramById(id_workout);
    }
    @PutMapping("/{idUser}/{idWorkout}")
    public void assignUserToWorkout(@PathVariable Long idUser,@PathVariable Integer idWorkout){
        serviceworkout.assignUserToWorkout(idWorkout,idUser);
    }
    @GetMapping("/user/{idUser}")
    public List<Workoutprogram> getWorkoutProgramsByUserId(@PathVariable Long idUser) {
        return serviceworkout.getWorkoutProgramByUserId(idUser);
    }
    @GetMapping("/nonuser/{idUser}")
    public List<Workoutprogram> getWorkoutProgramsByNonUserId(@PathVariable Long idUser) {
        return serviceworkout.getWorkoutProgramByNonUserId(idUser);
    }
}