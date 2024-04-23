package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Comment;
import tn.spring.pispring.Entities.SubComment;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaces.SubCommentInterface;
import tn.spring.pispring.Repositories.CommentRepo;
import tn.spring.pispring.Repositories.SubCommentRepo;
import tn.spring.pispring.Repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SubCommentService implements SubCommentInterface {

    private SubCommentRepo subCommentRepo;
    private CommentRepo commentRepo;
    private UserRepo userRepo;

    @Override
    public List<SubComment> getSubCommentsByComment(long idComment) {
        Comment comment = commentRepo.findById(idComment).orElseThrow(() -> new RuntimeException("Comment not found"));
        return subCommentRepo.findAllByComment(comment);
    }

    @Override
    public SubComment getSubComment(long idSubComment) {
        return subCommentRepo.findById(idSubComment).orElseThrow(() -> new RuntimeException("SubComment not found"));
    }

    @Override
    public SubComment addSubComment(long idUser, long idComment, SubComment subComment) {
        User author = userRepo.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = commentRepo.findById(idComment).orElseThrow(() -> new RuntimeException("Comment not found"));
        subComment.setAuthor(author);
        subComment.setComment(comment);
        subComment.setCreatedAt(LocalDateTime.now());
        return subCommentRepo.save(subComment);
    }

    @Override
    public SubComment updateSubComment(long idSubComment, SubComment subComment) {
        SubComment existingSubComment = getSubComment(idSubComment);
        existingSubComment.setContent(subComment.getContent());
        return subCommentRepo.save(existingSubComment);
    }

    @Override
    public void deleteSubComment(long idSubComment) {
        subCommentRepo.deleteById(idSubComment);
        System.out.println("SubComment deleted!");
    }
}