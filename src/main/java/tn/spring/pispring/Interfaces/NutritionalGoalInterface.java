package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Food;
import tn.spring.pispring.Entities.NutritionTracking;
import tn.spring.pispring.Entities.NutritionalGoal;

import java.util.List;
import java.util.Optional;

public interface NutritionalGoalInterface {
    List<NutritionalGoal> retrieveAllNutGoals();

    NutritionalGoal addNutritionalGoal(NutritionalGoal nutritionalGoal);

    void removeNutritionalGoal(long idNutGoal);

    NutritionalGoal findNutritionalGoalById(long idNutGoal);

    NutritionalGoal updateNutritionalGoal(Long id, NutritionalGoal updatedGoal);

    public void associateNutritionTracking(NutritionalGoal nutritionalGoal, NutritionTracking nutritionTracking);

    public void dissociateNutritionTracking(NutritionalGoal nutritionalGoal, NutritionTracking nutritionTracking);
    public NutritionalGoal getDailyCalorieGoalForTracking(Long trackingId);
    NutritionalGoal getNutritionalGoalForTracking(Long trackingId);

}
