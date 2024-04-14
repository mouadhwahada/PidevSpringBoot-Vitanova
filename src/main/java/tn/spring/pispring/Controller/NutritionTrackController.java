package tn.spring.pispring.Controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Food;
import tn.spring.pispring.Entities.NutritionTracking;
import tn.spring.pispring.Entities.NutritionalGoal;
import tn.spring.pispring.Interfaces.NutritionTrackingInterface;
import tn.spring.pispring.Interfaces.NutritionalGoalInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor


@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class NutritionTrackController {
    @Autowired
    NutritionTrackingInterface InutritionTracking;
    @Autowired
    NutritionalGoalInterface nutritionalGoalInterface;

    @GetMapping("/getAllnutTrack")
    public List<NutritionTracking> retrieveAllNutTrack() {
        return InutritionTracking.retrieveAllNutTrack();
    }

    @PostMapping("/createNutritionTrack")
    public NutritionTracking addNutritionTrack(@RequestBody NutritionTracking nutritionTracking) {
        // Attribuez la date d'aujourd'hui à datenut
        nutritionTracking.setDateNut();
        return InutritionTracking.addNutritionTrack(nutritionTracking);
    }

    @PutMapping("/updateNutritionTrack/{id}")
    public ResponseEntity<NutritionTracking> updateNutritionTrack(@PathVariable("id") Long id, @RequestBody NutritionTracking updatedNutTrack) {
        NutritionTracking updated = InutritionTracking.updateNutritionTrack(id, updatedNutTrack);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteNutritionTrack/{idNutTrack}")
    public void removeNutritionTrack(@PathVariable Long idNutTrack) {
        InutritionTracking.removeNutritionTrack(idNutTrack);
    }

    @GetMapping("/findNutrionTrack/{id}")
    public NutritionTracking findNutritionTrackById(@PathVariable("id") long id) {
        return InutritionTracking.findNutritionTrackById(id);
    }

    @PostMapping("/{nutritionTrackingId}/food")
    public ResponseEntity<NutritionTracking> recordFoodIntake(@PathVariable Long nutritionTrackingId, @RequestBody Food food) {
        NutritionTracking nutritionTracking = InutritionTracking.findNutritionTrackById(nutritionTrackingId);
        if (nutritionTracking != null) {
            InutritionTracking.recordFoodIntake(nutritionTracking, food);
            return new ResponseEntity<>(nutritionTracking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/remaining-calories/{trackingId}")
    public ResponseEntity<Long> getRemainingCaloriesForTracking(@PathVariable("trackingId") Long trackingId) {
        long remainingCalories = InutritionTracking.getRemainingCaloriesForTracking(trackingId);
        return new ResponseEntity<>(remainingCalories, HttpStatus.OK);
    }





    @PostMapping("/associate-goal/{trackingId}/{goalId}")
    public ResponseEntity<String> associateNutritionalGoal(@PathVariable long trackingId, @PathVariable long goalId) {
        NutritionTracking nutritionTracking = InutritionTracking.findNutritionTrackById(trackingId);
        NutritionalGoal nutritionalGoal = nutritionalGoalInterface.findNutritionalGoalById(goalId);

        if (nutritionTracking == null) {
            return ResponseEntity.badRequest().body("Nutrition tracking not found");
        }

        if (nutritionalGoal == null) {
            return ResponseEntity.badRequest().body("Nutritional goal not found");
        }

        InutritionTracking.associateNutritionalGoal(nutritionTracking, nutritionalGoal);
        return ResponseEntity.ok("Nutritional goal associated with nutrition tracking successfully");
    }

    @PostMapping("/dissociate-goal/{trackingId}")
    public ResponseEntity<String> dissociateNutritionalGoal(@PathVariable long trackingId) {
        NutritionTracking nutritionTracking = InutritionTracking.findNutritionTrackById(trackingId);

        if (nutritionTracking == null) {
            return ResponseEntity.badRequest().body("Nutrition tracking not found");
        }

        InutritionTracking.dissociateNutritionalGoal(nutritionTracking);
        return ResponseEntity.ok("Nutritional goal dissociated from nutrition tracking successfully");
    }
    @PostMapping("/calculateDailyCalorieGoal")
    public ResponseEntity<Long> calculateDailyCalorieGoal(
            @RequestParam int weight,
            @RequestParam int height,
            @RequestParam String goal,
            @RequestParam int duration) {

        // Appel de votre service pour calculer l'objectif calorique quotidien
        long dailyCalorieGoal = InutritionTracking.calculateDailyCalorieGoal(weight, height, goal, duration);

        return new ResponseEntity<>(dailyCalorieGoal, HttpStatus.OK);
    }
    @GetMapping("/lastNutritionTracking")
    public ResponseEntity<NutritionTracking> getLastNutritionTracking() {
        NutritionTracking lastTracking = InutritionTracking.getLastNutritionTracking();
        if (lastTracking != null) {
            return ResponseEntity.ok(lastTracking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/nutritionTrackingsByNutritionalGoalId/{nutritionalGoalId}")
    public ResponseEntity<List<NutritionTracking>> getNutritionTrackingsByNutritionalGoalId(@PathVariable Long nutritionalGoalId) {
        List<NutritionTracking> nutritionTrackings =InutritionTracking.getNutritionTrackingsByNutritionalGoalId(nutritionalGoalId);
        return ResponseEntity.ok(nutritionTrackings);
    }

}