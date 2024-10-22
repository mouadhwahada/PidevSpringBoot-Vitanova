package tn.spring.pispring.ServiceIMP;

import tn.spring.pispring.Entities.User;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Interfaces.OTPInterface;
import tn.spring.pispring.ServiceseExternes.MailSenderService;
import tn.spring.pispring.dto.OTP;
import tn.spring.pispring.repo.OTPRepository;
import tn.spring.pispring.repo.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
@Service
@AllArgsConstructor

public class OTPServiceIMP implements OTPInterface {
    @Autowired
    OTPRepository otpRepository;
    @Autowired
    MailSenderService mailSending;
@Autowired
UserRepository userRepository;
    @Override
    public OTP GenerateOTp() {
        // Generate a 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, 5); // Set the expiration time to 5 minutes
        Date expiredDate = calendar.getTime();

        OTP otpObject = new OTP();
        otpObject.setIdentification(String.valueOf(otp));
        otpObject.setExpiredDate(expiredDate);
        otpRepository.save(otpObject);
        return otpObject;
    }

    @Override
    public Boolean VerifOTP(String identification) {
        // Retrieve the OTP object from the repository based on the identification
        OTP otp = otpRepository.findByIdentification(identification);

        // Check if the OTP object is null (i.e., not found)
        if (otp == null) {
            return false; // OTP does not exist, so it's invalid
        }

        // Get the expiration date of the OTP
        Date expiredDate = otp.getExpiredDate();

        // Get the current date and time
        Date now = new Date();

        // Check if the current date and time is before or equal to the expiration date
        return !now.after(expiredDate);
    }

    @Override
    public void userstatus(String emailuser, Boolean result) {
        if(result==true){
        Optional<User> user = userRepository.findByEmail(emailuser);
        user.get().setValid(true);
        userRepository.save(user.get());}
    }


    @Override
    public OTP ResendOTP(String email) {
        // Check if the existing OTP has expired

        OTP newOtp= GenerateOTp();
        Optional<User> user = userRepository.findByEmail(email);
        String verificationCode = newOtp.getIdentification() ;// Replace with your actual verification code
        String htmlMessage = "<div style='border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;'>"
                + "Soyez le bienvenue dans notre plateforme /n"
                + "Veuillez utiliser ce lien pour vous authentifier : /n "
                + "<strong>Verification Code ! max 5 min ! :</strong>  /n" + verificationCode +
                 "</div>";
        try {
            mailSending.send(user.get().getEmail(), "Welcome"+ user.get().getName() , htmlMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newOtp;
    }

    @Override
    public void DeleteALLOTP() {
        otpRepository.deleteAll();
    }
}
