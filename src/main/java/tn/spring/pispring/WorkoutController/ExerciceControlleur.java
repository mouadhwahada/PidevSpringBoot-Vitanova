package tn.spring.pispring.WorkoutController;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.Workoutprogram;
import tn.spring.pispring.Serviceworkout.ExerciceService;
import tn.spring.pispring.Serviceworkout.WorkoutService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/exercises")
@AllArgsConstructor
public class ExerciceControlleur {
    private ExerciceService exerciceService;

    @PostMapping
    public Exercise creatExercice(@RequestBody Exercise exercise) {
        return exerciceService.creatExercice(exercise);
    }

    @DeleteMapping("/{id_Exercice}")
    public void deleteExercice(@PathVariable("id_Exercice") Integer id_Exercice) {
        exerciceService.deleteExercice(id_Exercice);

    }

    @PutMapping
    public Exercise Updatexrercice(@RequestBody Exercise exercise) {
        return exerciceService.Updatexrercice(exercise);
    }

    @GetMapping
    public List<Exercise> getAllExercice() {
        return exerciceService.getAllExercice();
    }



    @GetMapping("/{id_Exercice}")
    public Exercise getExerciceById(@PathVariable Integer id_Exercice) {
        return exerciceService.getExerciceById(id_Exercice);
    }
    @PostMapping("/addExerciseToDay/{exerciseDayId}")
    public void addExerciseToDay(@RequestBody Exercise exercise, @PathVariable Integer exerciseDayId) {
        System.out.println(exercise.getId_Exercice());
        exerciceService.addExerciseToDay(exercise, exerciseDayId);
    }


    @GetMapping("/exercise-day/{id}")
    public List<Exercise> getAllExercice(@PathVariable Integer id) {
        return exerciceService.getExercisesByExcerciseDayId(id);
    }
    @GetMapping("/are-all-completed/{userId}/{exerciseDayId}")
    public boolean areAllExercisesCompletedForDay(@PathVariable Long userId, @PathVariable Integer exerciseDayId) {
        return exerciceService.areAllExercisesCompletedForDay(userId, exerciseDayId);
    }
}


