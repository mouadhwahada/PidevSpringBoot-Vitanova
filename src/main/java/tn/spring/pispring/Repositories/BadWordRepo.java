package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.BadWord;

@Repository
public interface BadWordRepo extends JpaRepository<BadWord, Long> {
}
