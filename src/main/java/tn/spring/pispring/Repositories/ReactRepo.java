package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Comment;
import tn.spring.pispring.Entities.Post;
import tn.spring.pispring.Entities.React;

import java.util.List;

@Repository
public interface ReactRepo extends JpaRepository<React, Long> {
    List<React> findAllByPost(Post post);

    @Query("SELECT r FROM React r where r.author.id= :idUser and r.post.id= :idPost")
    React getTypeReact(@Param("idUser") long idUser, @Param("idPost") long idPost);
}
