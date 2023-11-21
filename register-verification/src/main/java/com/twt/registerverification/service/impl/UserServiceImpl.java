package com.twt.registerverification.service.impl;

import com.twt.registerverification.model.ConfirmationToken;
import com.twt.registerverification.model.User;
import com.twt.registerverification.repository.ConfirmationTokenRepository;
import com.twt.registerverification.repository.UserRepository;
import com.twt.registerverification.service.EmailService;
import com.twt.registerverification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;

    @Override
    public ResponseEntity<?> saveUser(User user) {
        if (userRepository.existsByUserEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("Error: Please Use A different Email, This already exists");
        }
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("your-email@email.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Please Complete Registration !!!");
        mailMessage.setText("To confirm your account registration, please click the link below :"+"\n"+
                "http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);
        System.out.println("Confirmation Toke -  " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify your account creation by clicking the link in your email.");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String token) {
        ConfirmationToken token1 = confirmationTokenRepository.findByConfirmationToken(token);
        if(token1 != null) {
            User user = userRepository.findByUserEmailIgnoreCase(token1.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully");
        }
        return ResponseEntity.badRequest().body("Error: Email Verification failed!!!");
    }
}
