package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.spring.pispring.Entities.NutritionTracking;
import tn.spring.pispring.Entities.NutritionalGoal;

import java.util.List;
import java.util.Optional;

public interface NutritionTrackingRepo extends JpaRepository<NutritionTracking,Long> {

    List<NutritionTracking> findByNutritiongoal_IdNGoal(long idNutritionalgoal);
    NutritionTracking findFirstByOrderByIdNutTrackDesc();



}


