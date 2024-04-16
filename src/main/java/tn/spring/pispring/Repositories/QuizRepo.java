package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Long> {
    Quiz findBytitleQuiz( String titleQuiz);

    Quiz findQuizByIdQuiz(Long idQuiz);
}
