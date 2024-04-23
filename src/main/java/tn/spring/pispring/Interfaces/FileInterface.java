package tn.spring.pispring.Interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileInterface {

    void saveImage(MultipartFile file, long idPost);

    Resource loadImage(String fileName);
}
