package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Entities.Quiz;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
    Question findQuestionBytextQ(String textQ);
    @Query("SELECT q FROM Question q WHERE q.quiz.idQuiz = :quizId")
    List<Question> findByQuizId(@Param("quizId") Long quizId);
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answerList WHERE q.quiz.idQuiz = :quizId")
    List<Question> findQuestionsWithAnswersByQuizId(@Param("quizId") Long quizId);
}
