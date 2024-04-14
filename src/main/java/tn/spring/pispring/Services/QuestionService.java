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

 /*   public Question addQuestiontoQuiz(String titleQuiz, Question question) {
        Quiz quiz = quizRepo.findBytitleQuiz(titleQuiz);
        if (quiz != null) {
            question.setQuiz(quiz);
        }
            return questionRepo.save(question);


    }*/


    public Question addQuestionToQuiz(Long idQuiz, Long idQuestion) {
        Question question =findQuestionById(idQuestion);
        Quiz quiz = quizRepo.findQuizByIdQuiz(idQuiz);

        if (question != null && quiz != null) {
            quiz.getQuestionList().add(question);
            // Mettre à jour la table associative
            quizRepo.save(quiz);
        }

        return question;
    }
    public Question removeQuestionFromQuiz(Long idQuestion, Long idQuiz) {
        Quiz quiz=quizRepo.findQuizByIdQuiz(idQuiz);
        Question question = findQuestionById(idQuestion);

        if (question != null && quiz != null) {
            List<Question> questionList = quiz.getQuestionList();
            // Supprimer la réponse de la liste de réponses associée à la question
            boolean removed = questionList.removeIf(q -> q.getIdQuestion() == idQuestion);
            if (removed) {
                // Mettre à jour la table associée uniquement si la réponse a été retirée avec succès
                quizRepo.save(quiz);
                return question;
            }
        }
        return null;
    }


    public List<Answer> getAnswersForQuestion(Long idQuestion) {
        Question question = questionRepo.findById(idQuestion).orElse(null);
        if (question != null) {
            return question.getAnswerList();
        } else {
            return null;
        }
    }
    public List<Question> getQuestionsWithAnswersForQuiz(Long quizId) {
        Quiz quiz = quizRepo.findQuizByIdQuiz(quizId);
        if (quiz != null) {
            List<Question> questions = quiz.getQuestionList();
            for (Question question : questions) {
                List<Answer> answers = getAnswersForQuestion(question.getIdQuestion());
                question.setAnswerList(answers);
            }

            return questions;
        } else {
            return null;
        }
    }
}







