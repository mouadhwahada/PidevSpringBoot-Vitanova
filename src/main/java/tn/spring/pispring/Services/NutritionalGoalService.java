package tn.spring.pispring.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.NutritionTracking;
import tn.spring.pispring.Entities.NutritionalGoal;
import tn.spring.pispring.Interfaces.NutritionTrackingInterface;
import tn.spring.pispring.Interfaces.NutritionalGoalInterface;
import tn.spring.pispring.Repositories.NutritionTrackingRepo;
import tn.spring.pispring.Repositories.NutritionalGoalRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NutritionalGoalService implements NutritionalGoalInterface {
    private final NutritionalGoalRepo nutgoalRep;
    private final NutritionTrackingService nutracs;
    @Override
    public List<NutritionalGoal> retrieveAllNutGoals() {
        return nutgoalRep.findAll();
    }

    @Override
    public NutritionalGoal addNutritionalGoal(NutritionalGoal nutritionalGoal) {
        NutritionalGoal createdGoal = nutgoalRep.save(nutritionalGoal);
        if (createdGoal != null) {
            LocalDate today = LocalDate.now();
            LocalDate endDate = today.plusDays(nutritionalGoal.getDuration());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String endDateString = endDate.format(formatter);

            NutritionTracking nutritionTracking = new NutritionTracking();
            nutritionTracking.setNutritiongoal(createdGoal);
            nutritionTracking.setDateNut(endDateString);

            // Ajouter le suivi nutritionnel associé
            if (nutracs != null) {
                nutracs.addNutritionTrack(nutritionTracking);
            }
        }
        return createdGoal;
    }

    @Override
    public NutritionalGoal updateNutritionalGoal(Long id, NutritionalGoal updatedGoal) {
        Optional<NutritionalGoal> optionalGoal = nutgoalRep.findById(id);
        if (optionalGoal.isPresent()) {
            NutritionalGoal existingGoal = optionalGoal.get();
            existingGoal.setDaily_calorie_goal(updatedGoal.getDaily_calorie_goal());

            existingGoal.setGoal(updatedGoal.getGoal());
            existingGoal.setWeight_goal(updatedGoal.getWeight_goal());
            existingGoal.setDuration(updatedGoal.getDuration());
            existingGoal.setNuttrackingList(updatedGoal.getNuttrackingList());
            return nutgoalRep.save(existingGoal);
        } else {
            return null;
        }
    }


    @Override
    public void removeNutritionalGoal(long idNutGoal) {
        nutgoalRep.deleteById(idNutGoal);
    }

    @Override
    public NutritionalGoal findNutritionalGoalById(long idNutGoal) {
        return nutgoalRep.findById(idNutGoal).orElse(null);
    }
    @Override
    public void associateNutritionTracking(NutritionalGoal nutritionalGoal, NutritionTracking nutritionTracking) {
        nutritionalGoal.getNuttrackingList().add(nutritionTracking);
        nutritionTracking.setNutritiongoal(nutritionalGoal);
        nutgoalRep.save(nutritionalGoal);
    }

    @Override
    public void dissociateNutritionTracking(NutritionalGoal nutritionalGoal, NutritionTracking nutritionTracking) {
        nutritionalGoal.getNuttrackingList().remove(nutritionTracking);
        nutritionTracking.setNutritiongoal(null);
        nutgoalRep.save(nutritionalGoal);
    }
    @Override
    public NutritionalGoal getDailyCalorieGoalForTracking(Long trackingId) {
        Optional<NutritionTracking> trackingOptional = nutTrackRepo.findById(trackingId);
        if (trackingOptional.isEmpty()) {
            throw new EntityNotFoundException("Nutrition tracking not found with ID: " + trackingId);
        }
        NutritionTracking tracking = trackingOptional.get();

        NutritionalGoal goal = tracking.getNutritiongoal();
        if (goal == null) {
            throw new IllegalStateException("No nutritional goal associated with the tracking");
        }

        return goal;
    }
    @Override
    public NutritionalGoal getNutritionalGoalForTracking(Long trackingId) {
        return nutgoalRep.findById(trackingId).get();
    }



    private final NutritionTrackingRepo nutTrackRepo;

}
