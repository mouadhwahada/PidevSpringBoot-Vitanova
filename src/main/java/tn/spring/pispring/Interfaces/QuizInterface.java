package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Quiz;

import java.util.List;

public interface QuizInterface {
    Quiz addQuiz(Quiz quiz);
    Quiz UpdateQuiz(Long id, Quiz updatedQuiz);
    void deleteQuiz(long id);
    List<Quiz> findAllQuizzes();
    Quiz findQuizById(long id);
}
