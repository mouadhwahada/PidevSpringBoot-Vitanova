package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Post;
import tn.spring.pispring.Entities.React;
import tn.spring.pispring.Entities.TypeReact;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaces.ReactInterface;
import tn.spring.pispring.Repositories.PostRepo;
import tn.spring.pispring.Repositories.ReactRepo;
import tn.spring.pispring.Repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReactService implements ReactInterface {


    private ReactRepo reactRepo;
    private PostRepo postRepo;
    private UserRepo userRepo;

    @Override
    public List<React> getReactByPost(long idPost) {
        Post post = postRepo.findById(idPost).orElseThrow(() -> new RuntimeException("Post not found"));
        return reactRepo.findAllByPost(post);
    }

    @Override
    public React getReact(long idReact) {
        return reactRepo.findById(idReact).orElseThrow(() -> new RuntimeException("React not found"));
    }

    @Override
    public React addReact(long idUser, long idPost, String type) {
        User author = userRepo.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepo.findById(idPost).orElseThrow(() -> new RuntimeException("Post not found"));
        React react = reactRepo.getTypeReact(idUser, idPost);

        if (react != null) {
            if (react.getTypeReact().toString().equals(type)) {
                react.setTypeReact(type.equals("Like") ? TypeReact.Dislike : TypeReact.Like);
            } else {
                react.setTypeReact(TypeReact.valueOf(type));
            }
        } else {
            react = new React();
            react.setAuthor(author);
            react.setPost(post);
            react.setCreatedAt(LocalDateTime.now());
            react.setTypeReact(TypeReact.valueOf(type));
        }

        return reactRepo.save(react);
    }

    @Override
    public TypeReact getTypeReact(long idUser, long idPost) {
        React react=reactRepo.getTypeReact(idUser, idPost);
        return react.getTypeReact();
    }
    @Override
    public void deleteReact(long idUser, long idPost) {
        React react = reactRepo.getTypeReact(idUser, idPost);
        if (react != null) {
            reactRepo.deleteById(react.getId());
        } else {
            throw new RuntimeException("React not found for the given user and post IDs");
        }
    }
}
