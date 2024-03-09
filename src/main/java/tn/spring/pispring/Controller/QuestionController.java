package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Question;
import tn.spring.pispring.Services.QuestionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class QuestionController {
    @Autowired
    QuestionService questionService;

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

    @PostMapping("/add-to-quiz/")
    public ResponseEntity<Question> addQuestiontoQuiz(@RequestBody Map<String, String> questionData) {
        String titleQuiz = questionData.get("titleQuiz");
        String charQ = questionData.get("charQ");
        String textQ = questionData.get("textQ");

        Question addedQuestion = questionService.addQuestiontoQuiz(titleQuiz, charQ, textQ);

        if (addedQuestion != null) {
            return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
