package tn.spring.pispring.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import tn.spring.pispring.Entities.Food;
import tn.spring.pispring.Entities.NutritionTracking;
import tn.spring.pispring.Entities.NutritionalGoal;
import tn.spring.pispring.Interfaces.NutritionTrackingInterface;
import tn.spring.pispring.Interfaces.NutritionalGoalInterface;
import tn.spring.pispring.Repositories.NutritionTrackingRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NutritionTrackingService implements NutritionTrackingInterface {
    private final NutritionTrackingRepo nutTrackRepo;


    @Override
    public List<NutritionTracking> retrieveAllNutTrack() {
        return nutTrackRepo.findAll();
    }

    @Override
    public NutritionTracking addNutritionTrack(NutritionTracking nutritionTracking) {
        return nutTrackRepo.save(nutritionTracking);
    }

    @Override
    public NutritionTracking updateNutritionTrack(Long id, NutritionTracking updatedNutTrack) {
        Optional<NutritionTracking> optionalNutTrack = nutTrackRepo.findById(id);
        if (optionalNutTrack.isPresent()) {
            NutritionTracking existingNutTrack = optionalNutTrack.get();
            // Mettre à jour les propriétés de l'élément existant avec les nouvelles valeurs
            existingNutTrack.setDateNut(updatedNutTrack.getDateNut());
            existingNutTrack.setTotal_calories(updatedNutTrack.getTotal_calories());
            // Mettre à jour d'autres propriétés si nécessaire

            // Enregistrer les modifications dans la base de données
            return nutTrackRepo.save(existingNutTrack);
        } else {
            // Gérer le cas où l'élément à mettre à jour n'existe pas
            return null;
        }
    }


    @Override
    public void removeNutritionTrack(long idNutTrack) {
     nutTrackRepo.deleteById(idNutTrack);
    }

    @Override
    public NutritionTracking findNutritionTrackById(long id) {
        return nutTrackRepo.findById(id).orElse(null);
    }
    @Override
    public void recordFoodIntake(NutritionTracking tracking, Food food) {
        tracking.getConsumedFoods().add(food);
        // Update total calories in tracking based on consumed food
        long totalCalories = tracking.getTotal_calories() + food.getCalories_per_serving();
        tracking.setTotal_calories(totalCalories);
        // Save or update tracking entity
        nutTrackRepo.save(tracking);
    }
    @Override
    public long calculateDailyCalorieQuota(NutritionalGoal goal) {
        return goal.getDaily_calorie_goal() / goal.getDuration();
    }

   @Override
    public NutritionTracking getLastNutritionTracking() {
        NutritionTracking lastTracking = nutTrackRepo.findFirstByOrderByIdNutTrackDesc();
        return lastTracking;
    }
    @Override
    public List<NutritionTracking> getNutritionTrackingsByNutritionalGoalId(Long nutritionalGoalId) {
        return nutTrackRepo.findByNutritiongoal_IdNGoal(nutritionalGoalId);
    }



    @Override
    public void associateNutritionalGoal(NutritionTracking nutritionTracking, NutritionalGoal nutritionalGoal) {
        nutritionTracking.setNutritiongoal(nutritionalGoal);
        nutTrackRepo.save(nutritionTracking);
    }
    @Override
    // Méthode pour dissocier un objectif nutritionnel d'un suivi nutritionnel
    public void dissociateNutritionalGoal(NutritionTracking nutritionTracking) {
        nutritionTracking.setNutritiongoal(null);
        nutTrackRepo.save(nutritionTracking);
    }
    public long getConsumedCaloriesForTracking(Long trackingId) {
        NutritionTracking tracking = nutTrackRepo.findById(trackingId)
                .orElseThrow(() -> new EntityNotFoundException("Nutrition tracking not found with ID: " + trackingId));

        // Calculer les calories consommées pour le suivi nutritionnel donné
        long consumedCalories = 0;
        for (Food food : tracking.getConsumedFoods()) {
            consumedCalories += food.getCalories_per_serving();
        }

        return consumedCalories;
    }
    @Override
    public long getRemainingCaloriesForTracking(Long trackingId) {
        NutritionTracking tracking = nutTrackRepo.findById(trackingId)
                .orElseThrow(() -> new EntityNotFoundException("Nutrition tracking not found with ID: " + trackingId));

        NutritionalGoal goal = tracking.getNutritiongoal();
        if (goal == null) {
            throw new IllegalStateException("No nutritional goal associated with the tracking.");
        }

        long consumedCalories = getConsumedCaloriesForTracking(trackingId);
        long remainingCalories = goal.getDaily_calorie_goal() - consumedCalories;

        return remainingCalories;
    }


    @Override

    public long calculateDailyCalorieGoal(int weight, int height, String goal, int duration) {
        if (weight <= 0 || height <= 0 || duration <= 0) {
            throw new IllegalArgumentException("weight, height, and duration must be positive integers.");
        }

        // Définir les coefficients pour le calcul du BMR
        final double MALE_BASE_BMR = 66.47;
        final double MALE_WEIGHT_COEFFICIENT = 13.75;
        final double MALE_HEIGHT_COEFFICIENT = 5.003;

        // Calculer le BMR en fonction du poids et de la taille
        double bmr = MALE_BASE_BMR + (MALE_WEIGHT_COEFFICIENT * weight) + (MALE_HEIGHT_COEFFICIENT * height);

        // Définir le facteur d'activité en fonction de l'objectif
        double activityFactor;
        switch (goal.toLowerCase()) {
            case "maintain weight":
                activityFactor = 1.55; // Facteur d'activité moyen pour le maintien du poids
                break;
            case "lose weight":
                activityFactor = 1.375; // Facteur d'activité léger pour la perte de poids
                break;
            case "gain weight":
                activityFactor = 1.725; // Facteur d'activité élevé pour le gain de poids
                break;
            default:
                throw new IllegalArgumentException("Invalid goal. Valid options are 'maintain weight', 'lose weight', or 'gain weight'.");
        }

        // Calculer les calories quotidiennes moyennes en multipliant le BMR par le facteur d'activité
        double dailyCalorieGoal = bmr * activityFactor;

        // Diviser les calories par la durée pour obtenir les calories quotidiennes cibles
        long dailyCaloriesTarget = Math.round(dailyCalorieGoal / duration);

        return dailyCaloriesTarget;
    }



}
