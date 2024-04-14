package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Question;

import java.util.List;

public interface QuestionInterface {
    Question addQuestion(Question question);
    Question UpdateQuestion(Long id, Question updatedQuestion);
    void deleteQuestion(long id);
    List<Question> findAllQuestions();
    Question findQuestionById(long id);

}
