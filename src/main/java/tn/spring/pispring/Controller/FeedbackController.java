package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Feedback;
import tn.spring.pispring.Services.FeedbackService;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
@RestController

@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    // Dans votre contrôleur FeedbackController
    @PostMapping("/feedback")
    public ResponseEntity<Feedback> createFeedback(@RequestParam long userId,
                                                   @RequestParam int rating,
                                                   @RequestParam String orderCode) {

        Feedback feedback = feedbackService.createFeedback(userId, rating, orderCode);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(feedback);
    }
    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }


    @GetMapping("/feedback/average-rating")
    public ResponseEntity<Double> getAverageRating() {
        double averageRating = feedbackService.calculateAverageRating();
        return ResponseEntity.ok(averageRating);
    }

}
