package tn.spring.pispring.repositoriesworkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Workoutprogram;

import java.util.Optional;

@Repository
public interface Workoutrepository extends JpaRepository<Workoutprogram,Integer> {
    Workoutprogram deleteByName(String name);
}
