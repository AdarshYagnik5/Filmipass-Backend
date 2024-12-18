package com.FilmiPass.Service;

import com.FilmiPass.Modal.User;
import com.FilmiPass.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        String subject = "Welcome to FilmiPass!";
        String body = "Hi " + user.getFirstName() + " " + user.getLastName() + ",\n\nWelcome to FilmiPass! We're glad to have you on board.";

        emailService.sendMail(user.getEmail(),subject,body);

        return userRepo.save(user);
    }
}
