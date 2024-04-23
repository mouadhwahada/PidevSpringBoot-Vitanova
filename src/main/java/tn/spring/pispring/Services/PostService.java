package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Post;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Entities.BadWord;
import tn.spring.pispring.Interfaces.PostInterface;
import tn.spring.pispring.Repositories.BadWordRepo;
import tn.spring.pispring.Repositories.PostRepo;
import tn.spring.pispring.Repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService implements PostInterface {

    private PostRepo postRepo;

    private UserRepo userRepo;

    private BadWordRepo badWordRepo;

    private JavaMailSender emailSender;

    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public Post getPost(long idPost) {
        Post post = postRepo.findById(idPost).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setNbViews(post.getNbViews()+1);
        return postRepo.save(post);
    }

    @Override
    public Post addPost(long idUser, Post post) {
        User author = userRepo.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));

        if (containsBadWords(post.getTitle()) || containsBadWords(post.getContent())) {
            throw new RuntimeException("Post contains bad words");
        }

        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());
        post.setNbViews(0);

        String email = author.getUserName() + "@gmail.com";
        String subject = "Vita Nova Staff";
        String body = "You have added a new post! ";

        sendEmail(email, subject, body);
        return postRepo.save(post);
    }

    @Override
    public Post updatePost(long idPost, Post post) {
        Post existingPost = getPost(idPost);

        if (containsBadWords(post.getTitle()) || containsBadWords(post.getContent())) {
            throw new RuntimeException("Post contains bad words");
        }

        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setUpdatedAt(LocalDateTime.now());
        return postRepo.save(existingPost);
    }

    @Override
    public void deletePost(long idPost) {
        Post post = getPost(idPost);
        post.setAuthor(null);
        postRepo.save(post);
        postRepo.deleteById(idPost);
        System.out.println("Post deleted!");
    }

    @Override
    public List<Post> getPostsByUser(long idUser)
    {
        return postRepo.findAllByAuthorId(idUser);
    }

    private boolean containsBadWords(String text) {
        List<BadWord> badWords = badWordRepo.findAll();

        for (BadWord badWord : badWords) {
            if (text.toLowerCase().contains(badWord.getWord().toLowerCase())) {
                return true;
            }
        }
        return false;
    }


    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("absirayen143@gmail.com\n");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
        System.out.println("Mail Sent successfully...");
    }

}
