package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Food;
import tn.spring.pispring.Entities.NutritionTracking;
import tn.spring.pispring.Entities.NutritionalGoal;

import java.util.List;

public interface NutritionTrackingInterface {
    List<NutritionTracking> retrieveAllNutTrack();
    NutritionTracking addNutritionTrack(NutritionTracking nutritionTracking);
    NutritionTracking updateNutritionTrack(Long id, NutritionTracking updatedNutTrack);
    void removeNutritionTrack(long idNutTrack);
    NutritionTracking findNutritionTrackById(long id);
    public void recordFoodIntake(NutritionTracking tracking, Food food);
    public long calculateDailyCalorieQuota(NutritionalGoal goal);

    public void associateNutritionalGoal(NutritionTracking nutritionTracking, NutritionalGoal nutritionalGoal);
    public void dissociateNutritionalGoal(NutritionTracking nutritionTracking);
    public long calculateDailyCalorieGoal(int weight, int height, String goal, int duration);
    long getRemainingCaloriesForTracking(Long trackingId);
    public NutritionTracking getLastNutritionTracking();
    public List<NutritionTracking> getNutritionTrackingsByNutritionalGoalId(Long nutritionalGoalId);


}
