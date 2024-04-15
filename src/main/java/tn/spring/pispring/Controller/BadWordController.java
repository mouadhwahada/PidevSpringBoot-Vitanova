package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.BadWord;
import tn.spring.pispring.Interfaces.BadWordInterface;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/badword")
@CrossOrigin("*")
public class BadWordController {

    private BadWordInterface badWordInterface;

    @GetMapping
    public ResponseEntity<List<BadWord>> getAllBadWords() {
        List<BadWord> badWords = badWordInterface.getAllBadWords();
        return ResponseEntity.ok(badWords);
    }

    @PostMapping
    public ResponseEntity<BadWord> addBadWord(@RequestBody BadWord badWord) {
        BadWord savedBadWord = badWordInterface.addBadWord(badWord);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBadWord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadWord(@PathVariable Long id) {
        badWordInterface.deleteBadWord(id);
        return ResponseEntity.noContent().build();
    }
}