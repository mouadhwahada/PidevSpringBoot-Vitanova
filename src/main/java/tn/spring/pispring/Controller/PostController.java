package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.pispring.Entities.Post;
import tn.spring.pispring.Entities.React;
import tn.spring.pispring.Entities.TypeReact;
import tn.spring.pispring.Interfaces.FileInterface;
import tn.spring.pispring.Interfaces.PostInterface;
import tn.spring.pispring.Interfaces.ReactInterface;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {

    PostInterface postInterface;

    ReactInterface reactInterface;

    FileInterface fileInterface;
    @GetMapping("/getAll")
    public List<Post> getAllPosts() {
        return postInterface.getAllPosts();
    }

    @PutMapping("/getPost/{idPost}")
    public Post getPost(@PathVariable long idPost) {
        return postInterface.getPost(idPost);
    }

    @PostMapping("/addPost/{idUser}")
    public ResponseEntity<?> addPost(@PathVariable long idUser, @RequestBody Post post) {
        Post savedPost = postInterface.addPost(idUser, post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @PutMapping("/updatePost/{idPost}")
    public ResponseEntity<?> updatePost(@PathVariable long idPost, @RequestBody Post post) {
        Post updatedPost = postInterface.updatePost(idPost, post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }
    @DeleteMapping("/deletePost/{idPost}")
    public void deletePost(@PathVariable long idPost) {
        postInterface.deletePost(idPost);
    }

    @GetMapping("/getPostByUser/{idUser}")
    public List<Post> getPostsByUser(@PathVariable long idUser) {
        return postInterface.getPostsByUser(idUser);
    }

    @GetMapping("/getReactByPost/{idPost}")
    public List<React> getReactByPost(@PathVariable long idPost) {
        return reactInterface.getReactByPost(idPost);
    }

    @GetMapping("/getReact/{idReact}")
    public React getReact(@PathVariable long idReact) {
        return reactInterface.getReact(idReact);
    }

    @GetMapping("/getTypeReact/{idUser}/{idPost}")
    public TypeReact getTypeReact(@PathVariable long idUser, @PathVariable long idPost) {
        return reactInterface.getTypeReact(idUser, idPost);
    }

    @PostMapping("/addReact/{idUser}/{idPost}")
    public React addReact(@PathVariable long idUser, @PathVariable long idPost, @RequestBody String type) {
        return reactInterface.addReact(idUser, idPost, type);
    }

    @DeleteMapping("/deleteReact/{idUser}/{idPost}")
    public void deleteReact(@PathVariable long idUser, @PathVariable long idPost) {
        reactInterface.deleteReact(idUser,idPost);
    }

    @PostMapping("/saveImage/{idPost}")
    public ResponseEntity<?> saveImage(@RequestParam("file") MultipartFile file, @PathVariable long idPost) {
        fileInterface.saveImage(file, idPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/loadImage/{fileName}")
    public ResponseEntity<Resource> loadImage(@PathVariable String fileName) {
        Resource resource = fileInterface.loadImage(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
