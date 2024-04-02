package tn.spring.pispring.Services;

import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Entities.Quiz;
import tn.spring.pispring.Interfaces.AnswerInterface;
import tn.spring.pispring.Repositories.AnswerRepo;
import tn.spring.pispring.Repositories.QuestionRepo;
import tn.spring.pispring.Repositories.QuizRepo;

import java.util.*;

@Service
public class AnswerService implements AnswerInterface {
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    QuizRepo quizRepo;

    private Map<Long, Double> quizScores = new HashMap<>();


    @Override
    public Answer addAnswer(Answer answer) {
        return answerRepo.save(answer);
    }

    @Override
    public Answer UpdateAnswer(Long id, Answer updatedAnswer) {
        Optional<Answer> optionalAnswer = answerRepo.findById(id);

        if (optionalAnswer.isPresent()) {
            Answer existingAnswer = optionalAnswer.get();
            existingAnswer.setScore(updatedAnswer.getScore());
            existingAnswer.setTextAnswer(updatedAnswer.getTextAnswer());
            return answerRepo.save(existingAnswer);
        } else {

            return null;
        }
    }
    @Override
    public void deleteAnswer(long id) {
        answerRepo.deleteById(id);

    }

    @Override
    public List<Answer> findAllAnswers() {
        return answerRepo.findAll();
    }

    @Override
    public Answer findAnswerById(long id) {
        return answerRepo.findById(id).get();
    }

    public Answer addAnswerToQuestion(String textQ, Answer answer) {
        Question question = questionRepo.findQuestionBytextQ(textQ);
        if (question != null) {
            answer.setQuestion(question);
            return answerRepo.save(answer);
        }
        // Handle the case where the question with the given charQ is not found
        return null;
    }


    public List<Answer> getAnswersForQuestion(Long questionId) {
        List<Answer> allAnswers = answerRepo.findAll();
        List<Answer> answersForQuestion = new ArrayList<>();

        for (Answer answer : allAnswers) {
            if (answer.getQuestion().getIdQuestion().equals(questionId)) {
                answersForQuestion.add(answer);
            }
        }

        return answersForQuestion;
    }

    public List<Question> getQuestionsWithAnswersForQuiz(Long quizId) {
        List<Question> questionsForQuiz = new ArrayList<>();

        // Récupérer toutes les questions pour le quiz donné
        List<Question> allQuestions = questionRepo.findByQuizId(quizId);
        for (Question question : allQuestions) {
            // Récupérer les réponses associées à chaque question et les attribuer à la question
            List<Answer> answersForQuestion = answerRepo.findByQuestionId(question.getIdQuestion());
            question.setAnswerList(answersForQuestion);
            questionsForQuiz.add(question);
        }
        return questionsForQuiz;
    }
    public double calculateQuizScore(List<Long> selectedAnswerIds) {
        double totalScore = 0.0;
        for (Long answerId : selectedAnswerIds) {
            Answer answer = answerRepo.findById(answerId).orElse(null);
            if (answer != null) {
                totalScore += answer.getScore();
            }
        }
        return totalScore;
    }
    public void processQuizResult(Long quizId, List<Long> selectedAnswerIds) {
        double score = calculateQuizScore(selectedAnswerIds);
        quizScores.put(quizId, score);
    }


    public Map<Long, Double> getQuizScores() {
        return quizScores;
    }





}

