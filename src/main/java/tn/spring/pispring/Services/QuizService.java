package tn.spring.pispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Entities.Quiz;
import tn.spring.pispring.Interfaces.QuizInterface;
import tn.spring.pispring.Repositories.QuizRepo;


import org.webjars.NotFoundException;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Entities.Quiz;
import tn.spring.pispring.Interfaces.QuizInterface;
import tn.spring.pispring.Repositories.AnswerRepo;
import tn.spring.pispring.Repositories.QuestionRepo;
import tn.spring.pispring.Repositories.QuizRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements QuizInterface {
    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public Quiz UpdateQuiz(Long id, Quiz updatedQuiz) {
        Optional<Quiz> optionalQuiz = quizRepo.findById(id);

        if (optionalQuiz.isPresent()) {
            // Si le quiz existe, mettez à jour ses données
            Quiz existingQuiz = optionalQuiz.get();
            existingQuiz.setTitleQuiz(updatedQuiz.getTitleQuiz());
            existingQuiz.setTopicQuiz(updatedQuiz.getTopicQuiz());
            // Continuez de cette manière pour les autres propriétés...

            // Enregistrez le quiz mis à jour dans la base de données
            return quizRepo.save(existingQuiz);
        } else {
            // Si aucun quiz n'est trouvé avec l'ID donné, retournez null

            Quiz existingQuiz = optionalQuiz.get();
            existingQuiz.setTitleQuiz(updatedQuiz.getTitleQuiz());
            existingQuiz.setTopicQuiz(updatedQuiz.getTopicQuiz());
            return quizRepo.save(existingQuiz);
        } else {
            return null;
        }
    }

    @Override
    public void deleteQuiz(long id) {
        quizRepo.deleteById(id);

    }

    @Override
    public List<Quiz> findAllQuizzes() {
        return quizRepo.findAll();
    }

    @Override
    public Quiz findQuizById(long id) {
        return quizRepo.findById(id).get();
    }














}
