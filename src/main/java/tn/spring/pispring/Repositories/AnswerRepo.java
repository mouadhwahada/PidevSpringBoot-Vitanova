package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Entities.Question;

import java.util.List;

@Repository
public interface AnswerRepo extends JpaRepository<Answer,Long> {
  /*  @Query("SELECT a FROM Answer a WHERE a.question.idQuestion = :questionId")
    List<Answer> findByQuestionId(@Param("questionId") Long questionId);
*/

}
