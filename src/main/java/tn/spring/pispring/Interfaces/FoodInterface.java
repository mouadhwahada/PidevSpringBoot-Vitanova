package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Food;

import java.util.List;

public interface FoodInterface {
    List<Food> retrieveFood();
    Food addFood(Food food);
    Food updateFood(Long id, Food updatedFood);
    void removeFood(long idFood);
    Food findFoodById(long idFood);
    public Food getFoodDetailsByName(String foodName);
    public long getCaloriesForFood(String foodName);
    public List<Food> getNutritionAdvice(String goal);
}
