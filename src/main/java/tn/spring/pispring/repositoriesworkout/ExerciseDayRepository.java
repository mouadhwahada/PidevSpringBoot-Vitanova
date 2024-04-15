package tn.spring.pispring.repositoriesworkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.ExerciseDay;

import java.util.List;

@Repository
public interface ExerciseDayRepository extends JpaRepository<ExerciseDay,Integer> {
}
