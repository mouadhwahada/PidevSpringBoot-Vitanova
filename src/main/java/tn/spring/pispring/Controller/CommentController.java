package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Comment;
import tn.spring.pispring.Interfaces.CommentInterface;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

    CommentInterface commentInterface;
    @GetMapping("/getCommentsByPost/{idPost}")
    public List<Comment> getCommentsByPost(@PathVariable long idPost) {
        return commentInterface.getCommentsByPost(idPost);
    }

    @GetMapping("/getComment/{idComment}")
    public Comment getComment(@PathVariable long idComment) {
        return commentInterface.getComment(idComment);
    }

    @PostMapping("/addComment/{idUser}/{idPost}")
    public Comment addComment(@PathVariable long idUser, @PathVariable long idPost, @RequestBody Comment comment) {
        return commentInterface.addComment(idUser, idPost, comment);
    }

    @PutMapping("/updateComment/{idComment}")
    public Comment updateComment(@PathVariable long idComment, @RequestBody Comment comment) {
        return commentInterface.updateComment(idComment, comment);
    }

    @DeleteMapping("/deleteComment/{idComment}")
    public void deleteComment(@PathVariable long idComment) {
        commentInterface.deleteComment(idComment);
    }


}
