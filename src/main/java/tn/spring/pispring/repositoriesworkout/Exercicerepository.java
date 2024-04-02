package tn.spring.pispring.repositoriesworkout;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Exercise;

@Repository

public interface Exercicerepository extends JpaRepository<Exercise,Integer> {
}
