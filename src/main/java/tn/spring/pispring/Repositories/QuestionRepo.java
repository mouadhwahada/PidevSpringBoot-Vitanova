package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
}
