package tn.spring.pispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Interfaces.AnswerInterface;
import tn.spring.pispring.Repositories.AnswerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService implements AnswerInterface {
    @Autowired
    AnswerRepo answerRepo;


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
