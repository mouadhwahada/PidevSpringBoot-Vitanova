package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Comment;

import java.util.List;

public interface CommentInterface {
    List<Comment> getCommentsByPost(long idPost);

    Comment getComment(long idComment);

    Comment addComment(long idUser, long idPost, Comment comment);

    Comment updateComment(long idComment, Comment comment);

    void deleteComment(long idComment);
}
