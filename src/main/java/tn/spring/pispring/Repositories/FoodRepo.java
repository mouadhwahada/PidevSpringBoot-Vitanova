package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.spring.pispring.Entities.Food;

import java.util.List;

@Repository
public interface FoodRepo extends JpaRepository<Food,Long> {

    List<Food> findAll();
    Food findById(long idFood);

        Food findByNamefood(String foodName);
    @Query("SELECT f FROM Food f WHERE f.calories_per_serving <= :maxCalories")
    List<Food> findByCaloriesPerServingLessThanEqual(long maxCalories);

    @Query("SELECT f FROM Food f WHERE f.calories_per_serving >= :minCalories")
    List<Food> findByCaloriesPerServingGreaterThanEqual(long minCalories);
    }

