package tn.spring.pispring.Controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.NutritionTracking;
import tn.spring.pispring.Entities.NutritionalGoal;
import tn.spring.pispring.Interfaces.NutritionTrackingInterface;
import tn.spring.pispring.Interfaces.NutritionalGoalInterface;
import tn.spring.pispring.Services.NutritionTrackingService;
import tn.spring.pispring.Services.NutritionalGoalService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor


@CrossOrigin("http://localhost:4200")

@RestController
public class NutrionalGoalController {
    @GetMapping("/getAllnutgoal")
    public List<NutritionalGoal> retrieveAllNutGoals() {
        return INutgoal.retrieveAllNutGoals();
    }
   @PostMapping("/createNutritionalGoal")
    public NutritionalGoal addNutritionalGoal(@RequestBody NutritionalGoal nutritionalGoal) {
        return INutgoal.addNutritionalGoal(nutritionalGoal);
    }
    @PutMapping("/updateNutrionalGoal/{id}")
    public ResponseEntity<NutritionalGoal> updateNutritionalGoal(@PathVariable("id") Long id, @RequestBody NutritionalGoal updatedGoal) {
        NutritionalGoal updated = INutgoal.updateNutritionalGoal(id, updatedGoal);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


     @DeleteMapping("/deleteNutritionalGoal/{idNutGoal}")
    public void removeNutritionalGoal(@PathVariable("idNutGoal")long idNutGoal) {
        INutgoal.removeNutritionalGoal(idNutGoal);
    }
    @GetMapping("/findNutrionalGoal/{id}")
    public NutritionalGoal findNutritionalGoalById(@PathVariable("id")long idNutGoal) {
        return INutgoal.findNutritionalGoalById(idNutGoal);
    }
    @PostMapping("/{trackingId}/associatgoal/{goalId}")
    public void associateNutritionalGoal(@PathVariable("trackingId") long trackingId, @PathVariable("goalId") long goalId) {

        NutritionTracking nutritionTracking = nutritionTrackingInterface.findNutritionTrackById(trackingId);
        NutritionalGoal nutritionalGoal = INutgoal.findNutritionalGoalById(goalId);
        if (nutritionTracking != null && nutritionalGoal != null) {
            nutritionTrackingInterface.associateNutritionalGoal(nutritionTracking, nutritionalGoal);
        }
    }

    @DeleteMapping("/{trackingId}/dissociatgoal")
    public void dissociateNutritionalGoal(@PathVariable("trackingId") long trackingId) {
        NutritionTracking nutritionTracking = nutritionTrackingInterface.findNutritionTrackById(trackingId);
        if (nutritionTracking != null) {
            nutritionTrackingInterface.dissociateNutritionalGoal(nutritionTracking);
        }
    }
    @GetMapping("/tracking/{trackingId}")
    public ResponseEntity<NutritionalGoal> getDailyCalorieGoalForTracking(@PathVariable Long trackingId) {
        try {
            NutritionalGoal goal =INutgoal.getDailyCalorieGoalForTracking(trackingId);
            return ResponseEntity.ok().body(goal);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/nutritionalGoals/tracking/{trackingId}")
    public ResponseEntity<NutritionalGoal> getNutritionalGoalForTracking(@PathVariable Long trackingId) {
        NutritionalGoal goal = INutgoal.getNutritionalGoalForTracking(trackingId);
        if (goal != null) {
            return ResponseEntity.ok(goal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    private final NutritionalGoalService INutgoal;

    NutritionTrackingInterface nutritionTrackingInterface;

}
