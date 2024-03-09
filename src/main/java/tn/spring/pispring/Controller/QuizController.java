package tn.spring.pispring.Controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Quiz;
import tn.spring.pispring.Services.QuizService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class QuizController {
    QuizService quizService;

    @PostMapping("/addQuiz")
    public Quiz addQuiz(@org.springframework.web.bind.annotation.RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @PutMapping("/updateQuiz/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable("id") Long id, @org.springframework.web.bind.annotation.RequestBody  Quiz updatedQuiz) {
        // Appelez la méthode de mise à jour dans le service
        Quiz updated = quizService.UpdateQuiz(id, updatedQuiz);
        // Vérifiez si le quiz mis à jour est null
        if (updated != null) {
            // Si le quiz a été mis à jour avec succès, retournez le quiz mis à jour
            return ResponseEntity.ok(updated);
        } else {
            // Si aucun quiz avec l'ID donné n'a été trouvé, retournez un statut 404
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteQuiz/{id}")
    public void deleteQuiz(@PathVariable("id") long id) {
        quizService.deleteQuiz(id);
    }

    @GetMapping("/findAllQuizzes")
    public List<Quiz> findAllQuizzes() {
        return quizService.findAllQuizzes();
    }

    @GetMapping("/findQuizById/{id}")
    public Quiz findQuizById(@PathVariable("id")  long id) {
        return quizService.findQuizById(id);
    }
}
