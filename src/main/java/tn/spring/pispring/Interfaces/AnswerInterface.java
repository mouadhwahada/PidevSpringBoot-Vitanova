package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Answer;

import java.util.List;

public interface AnswerInterface {
   Answer addAnswer(Answer answer);
    Answer UpdateAnswer(Long id, Answer updatedAnswer);
    void deleteAnswer(long id);
    List<Answer> findAllAnswers();
    Answer findAnswerById(long id);
}
