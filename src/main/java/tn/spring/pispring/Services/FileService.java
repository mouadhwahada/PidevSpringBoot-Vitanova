package tn.spring.pispring.Services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.pispring.Entities.Post;
import tn.spring.pispring.Interfaces.FileInterface;
import tn.spring.pispring.Repositories.PostRepo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileService implements FileInterface {

    private final PostRepo postRepo;
    private final Path root = Paths.get("C:\\xampp\\htdocs\\imagespidev");


    @PostConstruct
    public void init() {
        if (!Files.exists(root)) {
            try {
                Files.createDirectories(root);
            } catch (IOException e) {
                System.err.println("Failed to create directory: " + e.getMessage());
            }
        }
    }

    @Override
    public void saveImage(MultipartFile file, long idPost) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = root.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            Post post = postRepo.findById(idPost).orElseThrow(() -> new RuntimeException("Post not found"));
            post.setImageName(fileName);
            postRepo.save(post);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource loadImage(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
