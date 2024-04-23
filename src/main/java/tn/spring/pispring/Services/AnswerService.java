package tn.spring.pispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Interfaces.AnswerInterface;
import tn.spring.pispring.Repositories.AnswerRepo;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Entities.Note;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Entities.Quiz;
import tn.spring.pispring.Interfaces.AnswerInterface;
import tn.spring.pispring.Repositories.AnswerRepo;
import tn.spring.pispring.Repositories.NoteRepo;
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
    @Autowired
    NoteRepo noteRepo;


    private Map<Long, Double> quizScores = new HashMap<>();


    @Override
    public Answer addAnswer(Answer answer) {
        return answerRepo.save(answer);
    }

    @Override
    public Answer UpdateAnswer(Long id, Answer updatedAnswer) {
        Optional<Answer> optionalAnswer = answerRepo.findById(id);

        if (optionalAnswer.isPresent()) {
            // Si la réponse existe, mettez à jour ses données
            Answer existingAnswer = optionalAnswer.get();
            existingAnswer.setScore(updatedAnswer.getScore());
            existingAnswer.setTextAnswer(updatedAnswer.getTextAnswer());
            // Continuez de cette manière pour les autres propriétés...

            // Enregistrez la réponse mise à jour dans la base de données
            return answerRepo.save(existingAnswer);
        } else {
            // Si aucune réponse n'est trouvée avec l'ID donné, retournez null
            return null;
        }
    }


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
}


 /*   public Answer addAnswerToQuestion(String textQ, Answer answer) {
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
*/
/*    public List<Question> getQuestionsWithAnswersForQuiz(Long quizId) {
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
    }*/
 public Answer addAnswerToQuestion(Long idQuestion, Long idAnswer) {
     Question question = questionRepo.findQuestionByIdQuestion(idQuestion);
     Answer answer = findAnswerById(idAnswer);

     if (question != null && answer != null) {
         question.getAnswerList().add(answer);
         // Mettre à jour la table associative
         questionRepo.save(question);
     }

     return answer;
 }

    public Answer removeAnswerFromQuestion(Long idQuestion, Long idAnswer) {
        Question question = questionRepo.findQuestionByIdQuestion(idQuestion);
        Answer answer = findAnswerById(idAnswer);

        if (question != null && answer != null) {
            List<Answer> answerList = question.getAnswerList();
            // Supprimer la réponse de la liste de réponses associée à la question
            boolean removed = answerList.removeIf(a -> a.getIdAnswer() == idAnswer);
            if (removed) {
                // Mettre à jour la table associée uniquement si la réponse a été retirée avec succès
                questionRepo.save(question);
                return answer;
            }
        }
        return null; // La réponse n'a pas été trouvée dans la liste de réponses associée à la question
    }






    public double calculateQuizScore(List<Long> selectedAnswerIds) {
        double totalScore = 0.0;
        for (Long answerId : selectedAnswerIds) {
            Answer answer = answerRepo.findById(answerId).orElse(null);
            if (answer != null) {
                totalScore += answer.getScore();

            }
        }
            Note note = new Note();
            note.setValueNote(totalScore);
        if (totalScore >= 75 && totalScore <= 100) {
             note.setDescNote("Your mental health is at a critical level. Seeking professional help is highly recommended.");
        } else if (totalScore >= 40 && totalScore < 75) {
            note.setDescNote("Your mental health condition requires attention. It's important to address these challenges.");
        } else if (totalScore >= 10 && totalScore < 40) {
            note.setDescNote("Your mental health seems to be in a good state. Keep up the healthy habits!");
        }


            // Sauvegarde de la note dans la base de données
            noteRepo.save(note);

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

