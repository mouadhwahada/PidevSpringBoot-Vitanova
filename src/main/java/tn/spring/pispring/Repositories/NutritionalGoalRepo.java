package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.NutritionalGoal;
@Repository
public interface NutritionalGoalRepo extends JpaRepository<NutritionalGoal,Long> {



}

