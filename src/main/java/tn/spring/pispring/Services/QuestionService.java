package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Entities.Quiz;
import tn.spring.pispring.Interfaces.QuestionInterface;
import tn.spring.pispring.Repositories.AnswerRepo;
import tn.spring.pispring.Repositories.QuestionRepo;
import tn.spring.pispring.Repositories.QuizRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService implements QuestionInterface {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    AnswerRepo answerRepo;
    @Override
    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public Question UpdateQuestion(Long id, Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionRepo.findById(id);

        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setCharQ(updatedQuestion.getCharQ());
            existingQuestion.setTextQ(updatedQuestion.getTextQ());
            return questionRepo.save(existingQuestion);
        } else {
            return null;
        }
    }

    @Override
    public void deleteQuestion(long id) {
        questionRepo.deleteById(id);

    }

    @Override
    public List<Question> findAllQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public Question findQuestionById(long id) {
        return questionRepo.findById(id).get();
    }

    public Question addQuestiontoQuiz(String titleQuiz, Question question) {
        Quiz quiz = quizRepo.findBytitleQuiz(titleQuiz);
        if (quiz != null) {
            question.setQuiz(quiz);
        }
            return questionRepo.save(question);


    }



}



