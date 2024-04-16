package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Food;
import tn.spring.pispring.Interfaces.FoodInterface;
import tn.spring.pispring.Repositories.FoodRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoodService implements FoodInterface {
    FoodRepo foodRepo;
    @Override
    public List<Food> retrieveFood() {
        return (List<Food>) foodRepo.findAll();
    }

    @Override
    public Food addFood(Food food) {
        return foodRepo.save(food);
    }

    @Override

    public Food updateFood(Long id, Food updatedFood) {
        Optional<Food> optionalFood = foodRepo.findById(id);
        if (optionalFood.isPresent()) {
            Food existingFood = optionalFood.get();
            existingFood.setNamefood(updatedFood.getNamefood());
            existingFood.setCalories_per_serving(updatedFood.getCalories_per_serving());
            existingFood.setProtein_per_serving(updatedFood.getProtein_per_serving());
            existingFood.setCarbohydrates_per_Serving(updatedFood.getCarbohydrates_per_Serving());
            existingFood.setFat_per_Serving(updatedFood.getFat_per_Serving());
            existingFood.setFiber_per_Serving(updatedFood.getFiber_per_Serving());
            existingFood.setVitamins_per_Serving(updatedFood.getVitamins_per_Serving());
            existingFood.setMinerals_per_Serving(updatedFood.getMinerals_per_Serving());
            existingFood.setNuttrack(updatedFood.getNuttrack());
            return foodRepo.save(existingFood);
        } else {
            return null;
        }
    }


    @Override
    public void removeFood(long idFood) {
        foodRepo.deleteById(idFood);
    }

    @Override
    public Food findFoodById(long idFood) {
        return foodRepo.findById(idFood);
    }


 @Override
    public Food getFoodDetailsByName(String foodName) {
        // Utilisez le repository FoodRepository pour rechercher l'aliment par son nom dans la base de données
        return foodRepo.findByNamefood(foodName);
    }
    @Override
    public long getCaloriesForFood(String foodName) {
        Food food = foodRepo.findByNamefood(foodName);
        return food != null ? food.getCalories_per_serving() : 0;
    }
    @Override
    public List<Food> getNutritionAdvice(String goal) {
        List<Food> suggestions = new ArrayList<>();

        if ("Gain weight".equals(goal)) {
            suggestions = foodRepo. findByCaloriesPerServingGreaterThanEqual(300);

        } else if ("Lose weight".equals(goal)) {
            suggestions = foodRepo.findByCaloriesPerServingLessThanEqual(200);

        } else {
            suggestions = foodRepo.findAll();

        }

        return suggestions;
    }
}
