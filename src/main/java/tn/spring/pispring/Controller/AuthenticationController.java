package tn.spring.pispring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.pispring.Entities.JwtRequest;
import tn.spring.pispring.Entities.JwtResponse;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Service.implementation.UserDetailsServiceImpl;
import tn.spring.pispring.config.JwtUtils;
import tn.spring.pispring.dto.EmailDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired

    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try
        {
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

            }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("User not found");
        }

        //////authenticate

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
       String token = this.jwtUtils.generateToken(userDetails);
       return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String username,String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        }catch (DisabledException e)
        {
            throw new Exception("USER DISABLED" + e.getMessage());
        }catch (BadCredentialsException e)
        {
            throw new Exception("Invalid credentials " + e.getMessage());
        }
    }

    //return
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
    }




}
