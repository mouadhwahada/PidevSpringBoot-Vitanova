package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Answer;
@Repository
public interface AnswerRepo extends JpaRepository<Answer,Long> {
}