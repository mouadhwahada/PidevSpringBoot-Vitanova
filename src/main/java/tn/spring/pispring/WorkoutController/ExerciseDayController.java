package tn.spring.pispring.WorkoutController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.ExerciseDay;
import tn.spring.pispring.Serviceworkout.ExerciseDayService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/exerciseday")
@AllArgsConstructor
public class ExerciseDayController {
    private ExerciseDayService exerciseDayService;
    @PostMapping
    public ResponseEntity<ExerciseDay> createExerciseDay(@RequestBody ExerciseDay exerciseDay){
        return ResponseEntity.ok(exerciseDayService.creatExerciceDay(exerciseDay));
    }
    @PutMapping
    public ResponseEntity<ExerciseDay> updateExerciseDay(@RequestBody ExerciseDay exerciseDay){
        return ResponseEntity.ok(exerciseDayService.UpdatexrerciceDay(exerciseDay));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExerciseDay(@PathVariable Integer id){
        exerciseDayService.deleteExerciceDay(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<ExerciseDay>> getAllExerciseDays(){
        return ResponseEntity.ok(exerciseDayService.getAllExerciseDay());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDay> getExerciseDayById(@PathVariable Integer id){
        return ResponseEntity.ok(exerciseDayService.getExerciseDayById(id));
    }
    @PostMapping("/{id}")
    public ResponseEntity<ExerciseDay> addExerciceDayToWorkout(@RequestBody ExerciseDay exerciseDay,@PathVariable Integer id){
        return ResponseEntity.ok(exerciseDayService.addExerciceDayToWorkout(exerciseDay,id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> completeDay(@PathVariable Integer id){
        exerciseDayService.completeDay(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/workout/{workoutId}")
    public List<ExerciseDay> getExerciseDaysByWorkoutId(@PathVariable Integer workoutId) {
        return exerciseDayService.getExerciseDaysByWorkoutId(workoutId);
    }

}
