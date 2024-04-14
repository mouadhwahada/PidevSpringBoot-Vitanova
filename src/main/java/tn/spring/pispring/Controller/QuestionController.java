package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Services.QuestionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class QuestionController {
    @Autowired
    QuestionService questionService;
  /*  @PostMapping("/addQuestiontoQuiz")
    public Question addQuestiontoQuiz(@RequestParam String titleQuiz,@RequestBody Question question) {
        return questionService.addQuestiontoQuiz(titleQuiz, question);
    }*/

    @PostMapping("/addQuestion")
    public Question addQuestion(@RequestBody Question question) {

        return questionService.addQuestion(question);
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        Question updated = questionService.UpdateQuestion(id, updatedQuestion);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable("id") long id) {
        questionService.deleteQuestion(id);
    }

    @GetMapping("/findAllQuestions")
    public List<Question> findAllQuestions() {
        return questionService.findAllQuestions();
    }

    @GetMapping("/findQuestionById/{id}")
    public Question findQuestionById(@PathVariable("id") long id) {
        return questionService.findQuestionById(id);
    }

    @GetMapping("/getAnswersForQuestion/{idQuestion}")
    public List<Answer> getAnswersForQuestion(@PathVariable Long idQuestion) {
        return questionService.getAnswersForQuestion(idQuestion);
    }
    @PostMapping("/addQuestionToQuiz/{idQuiz}/{idQuestion}")
    public Question addQuestionToQuiz(@PathVariable Long idQuiz, @PathVariable Long idQuestion) {
        return questionService.addQuestionToQuiz(idQuiz, idQuestion);
    }
    @DeleteMapping("/removeQuestionFromQuiz/{idQuiz}/{idQuestion}")
    public Question removeQuestionFromQuiz(@PathVariable Long idQuiz, @PathVariable Long idQuestion) {
        return questionService.removeQuestionFromQuiz(idQuestion, idQuiz);
    }

    @GetMapping("/getQuestionsWithAnswersForQuiz/{quizId}")
    public List<Question> getQuestionsWithAnswersForQuiz(@PathVariable Long quizId) {
        return questionService.getQuestionsWithAnswersForQuiz(quizId);
    }
}
