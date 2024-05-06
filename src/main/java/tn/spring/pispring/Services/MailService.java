package tn.spring.pispring.Services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {

    public void sendEmail(String tomail, String subject, String body) throws MessagingException {

        String from ="meknimed01@gmail.com";
        String password="fopoqprfsrpodgco";

        // Configuration des propriétés SMTP

        Properties props=new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");

        // Gestion de l'erreur de validation du certificat
        props.put("mail.smtp.ssl.trust", "*");

        //  responsable une connexion entre l'application Java et le serveur de messagerie (SMTP)
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Création du message

        Message message=new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(tomail));
        message.setSubject(subject);
        message.setContent(body,"text/html");
        // Envoi de l'e-mail

        Transport.send(message);
    }
}
