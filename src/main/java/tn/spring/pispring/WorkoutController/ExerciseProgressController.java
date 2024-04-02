package tn.spring.pispring.WorkoutController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.ExerciseProgress;
import tn.spring.pispring.Serviceworkout.ExerciseProgressService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/exerciseprogress")
@AllArgsConstructor
public class ExerciseProgressController {
    private ExerciseProgressService exerciseProgressService;
    @PostMapping("/{idUser}/{idExercise}")
    public ExerciseProgress addExerciseProgressAndAssignedToUserAndExercise(@RequestBody ExerciseProgress exerciseProgress,@PathVariable Long idUser,@PathVariable Integer idExercise){
        return exerciseProgressService.addExerciseProgressAndAssignedToUserAndExercise(exerciseProgress, idUser, idExercise);
    }
    @PutMapping
    public ExerciseProgress updateExerciseProgress(@RequestBody ExerciseProgress exerciseProgress){
        return exerciseProgressService.UpdatExerciseProgress(exerciseProgress);
    }
    @DeleteMapping("/{id}")
    public void deleteExerciseProgress(@PathVariable Integer id){
        exerciseProgressService.deleteExerciseProgress(id);
    }
    @GetMapping
    public List<ExerciseProgress> getAllExerciseProgress(){
        return exerciseProgressService.getAllExerciseProgress();
    }
    @GetMapping("/{id}")
    public ExerciseProgress getExerciseProgressById(@PathVariable Integer id){
        return exerciseProgressService.getExerciseProgressById(id);
    }
    @PutMapping("/{id}")
    public void markExerciseAsCompleted(@PathVariable Integer id){
        exerciseProgressService.markExerciseAsCompleted(id);
    }
    @PutMapping("/markCompleted/{userId}/{exerciseId}")
    public ResponseEntity<Void> markExerciseAsCompletedByUserAndExercise(
            @PathVariable Long userId,
            @PathVariable Integer exerciseId) {
        exerciseProgressService.markExerciseAsCompletedByUserAndExercise(userId, exerciseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/completedExercises/{userId}/{workoutId}")
    public ResponseEntity<List<Exercise>> getCompletedExercisesByUserAndWorkout(
            @PathVariable Long userId,
            @PathVariable Integer workoutId) {
        List<Exercise> completedExercises = exerciseProgressService.getCompletedExercisesByUserAndWorkout(userId, workoutId);
        return ResponseEntity.ok(completedExercises);
    }
}
