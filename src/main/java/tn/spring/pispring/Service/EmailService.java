package tn.spring.pispring.Service;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
  String senMail(MultipartFile[] file, String to, String[] cc, String subject, String body);
}
