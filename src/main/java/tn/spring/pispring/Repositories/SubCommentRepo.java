package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Comment;
import tn.spring.pispring.Entities.SubComment;

import java.util.List;

@Repository
public interface SubCommentRepo extends JpaRepository<SubComment, Long> {
    List<SubComment> findAllByComment(Comment comment);
}
