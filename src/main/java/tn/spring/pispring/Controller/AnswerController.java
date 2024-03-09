package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Answer;
import tn.spring.pispring.Services.AnswerService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class AnswerController {
    AnswerService answerService;

    @PostMapping("/addAnswer")
    public Answer addAnswer(@RequestBody Answer answer) {
        return answerService.addAnswer(answer);
    }

    @PutMapping("/updateAnswer/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable("id")  Long id, @RequestBody Answer updatedAnswer) {
        Answer updated = answerService.UpdateAnswer(id, updatedAnswer);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/deleteAnswer/{id}")
    public void deleteAnswer(@PathVariable("id") long id) {
        answerService.deleteAnswer(id);
    }

    @GetMapping("/findAllAnswers")
    public List<Answer> findAllAnswers() {
        return answerService.findAllAnswers();
    }

    @GetMapping("/findAnswerById/{id}")
    public Answer findAnswerById(@PathVariable ("id") long id) {
        return answerService.findAnswerById(id);
    }
}
