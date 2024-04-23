package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.SubComment;

import java.util.List;

public interface SubCommentInterface {
    List<SubComment> getSubCommentsByComment(long idComment);

    SubComment getSubComment(long idSubComment);

    SubComment addSubComment(long idUser, long idComment, SubComment subComment);

    SubComment updateSubComment(long idSubComment, SubComment subComment);

    void deleteSubComment(long idSubComment);
}
