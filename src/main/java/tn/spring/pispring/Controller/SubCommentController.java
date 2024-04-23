package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.SubComment;
import tn.spring.pispring.Interfaces.SubCommentInterface;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/subcomment")
@CrossOrigin("*")
public class SubCommentController {

    private final SubCommentInterface subCommentInterface;

    @GetMapping("/getSubCommentsByComment/{idComment}")
    public List<SubComment> getSubCommentsByComment(@PathVariable long idComment) {
        return subCommentInterface.getSubCommentsByComment(idComment);
    }

    @GetMapping("/getSubComment/{idSubComment}")
    public SubComment getSubComment(@PathVariable long idSubComment) {
        return subCommentInterface.getSubComment(idSubComment);
    }

    @PostMapping("/addSubComment/{idComment}")
    public SubComment addSubComment(@RequestParam long idUser, @PathVariable long idComment, @RequestBody SubComment subComment) {
        return subCommentInterface.addSubComment(idUser, idComment, subComment);
    }

    @PutMapping("/updateSubComment/{idSubComment}")
    public SubComment updateSubComment(@PathVariable long idSubComment, @RequestBody SubComment subComment) {
        return subCommentInterface.updateSubComment(idSubComment, subComment);
    }

    @DeleteMapping("/deleteSubComment/{idSubComment}")
    public void deleteSubComment(@PathVariable long idSubComment) {
        subCommentInterface.deleteSubComment(idSubComment);
    }
}