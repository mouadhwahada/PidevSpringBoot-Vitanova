package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Post;

import java.util.List;

public interface PostInterface {
    List<Post> getAllPosts();

    Post getPost(long idPost);

    Post addPost(long idUser, Post post);

    Post updatePost(long idPost, Post post);

    void deletePost(long idPost);

    List<Post> getPostsByUser(long idUser);
}
