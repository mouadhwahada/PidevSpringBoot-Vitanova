package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Comment;
import tn.spring.pispring.Entities.Post;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaces.CommentInterface;
import tn.spring.pispring.Repositories.CommentRepo;
import tn.spring.pispring.Repositories.PostRepo;
import tn.spring.pispring.Repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements CommentInterface {

    private CommentRepo commentRepo;
    private PostRepo postRepo;
    private UserRepo userRepo;

    @Override
    public List<Comment> getCommentsByPost(long idPost) {
        Post post = postRepo.findById(idPost).orElseThrow(() -> new RuntimeException("Post not found"));
        return commentRepo.findAllByPost(post);
    }

    @Override
    public Comment getComment(long idComment) {
        return commentRepo.findById(idComment).orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @Override
    public Comment addComment(long idUser, long idPost, Comment comment) {
        User author = userRepo.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
                 Post post = postRepo.findById(idPost).orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setAuthor(author);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepo.save(comment);
    }

    @Override
    public Comment updateComment(long idComment, Comment comment) {
        Comment existingComment = getComment(idComment);
        existingComment.setContent(comment.getContent());
        return commentRepo.save(existingComment);
    }

    @Override
    public void deleteComment(long idComment) {
        commentRepo.deleteById(idComment);
        System.out.println("Comment deleted!");
    }
}