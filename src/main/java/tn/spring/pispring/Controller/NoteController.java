package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Note;
import tn.spring.pispring.Services.NoteService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class NoteController {
    @Autowired
    NoteService noteService;

    @PostMapping("/addNote")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }
   /* @PostMapping("/addNoteToQuiz")
    public Note addNoteToQuiz(@RequestParam String titleQuiz,@RequestBody Note note) {
        return noteService.addNoteToQuiz(titleQuiz, note);
    }*/

    @PutMapping("/UpdateNote/{id}")
    public Note UpdateNote(@PathVariable("id") Long id,@RequestBody Note updatedNote) {
        return noteService.UpdateNote(id, updatedNote);
    }

    @DeleteMapping("/deleteNote/{id}")
    public void deleteNote(@PathVariable("id") long id) {
        noteService.deleteNote(id);
    }

    @GetMapping("/findAllNotes")
    public List<Note> findAllNotes() {
        return noteService.findAllNotes();
    }

    @GetMapping("/findNoteById/{id}")
    public Note findNoteById(@PathVariable ("id") long id) {
        return noteService.findNoteById(id);
    }
    @PostMapping(value = "/evaluateQuizScore/{quizScore}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String evaluateQuizScore(@PathVariable("quizScore") double quizScore) {
        return noteService.evaluateQuizScore(quizScore);
    }

    @GetMapping("/StatisticsOfNotes")
    public double[] StatisticsOfNotes() {
        return noteService.StatisticsOfNotes();
    }
}
