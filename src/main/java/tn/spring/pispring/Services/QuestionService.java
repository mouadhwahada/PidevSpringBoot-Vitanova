package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Entities.Quiz;
import tn.spring.pispring.Interfaces.QuestionInterface;
import tn.spring.pispring.Repositories.QuestionRepo;
import tn.spring.pispring.Repositories.QuizRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService implements QuestionInterface {
    @Autowired
    QuestionRepo questionRepo;
    QuizRepo quizRepo;
    @Override
    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public Question UpdateQuestion(Long id, Question updatedQuestion) {
        // Recherchez la question existante en utilisant son ID
        Optional<Question> optionalQuestion = questionRepo.findById(id);

        if (optionalQuestion.isPresent()) {
            // Si la question existe, mettez à jour ses données
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setCharQ(updatedQuestion.getCharQ());
            existingQuestion.setTextQ(updatedQuestion.getTextQ());
            // Continuez de cette manière pour les autres propriétés...

            // Enregistrez la question mise à jour dans la base de données
            return questionRepo.save(existingQuestion);
        } else {
            // Si aucune question n'est trouvée avec l'ID donné, retournez null
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

    public Question addQuestiontoQuiz(String titleQuiz, String charQ, String textQ) {
        Quiz quiz = quizRepo.findBytitleQuiz(titleQuiz);

        if (quiz != null) {
            Question question = new Question();
            question.setCharQ(charQ);
            question.setTextQ(textQ);
            question.setQuiz(quiz);

            quiz.getQuestionList().add(question);
            return questionRepo.save(question);
        } else {
            return null;
        }
    }

}
