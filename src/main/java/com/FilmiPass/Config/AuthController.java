package com.FilmiPass.Config;

import com.FilmiPass.Modal.JwtRequest;
import com.FilmiPass.Modal.JwtResponse;
import com.FilmiPass.Modal.User;
import com.FilmiPass.Security.JwtHelper;
import com.FilmiPass.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
        doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtHelper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder().jwtToken(token)
                .username(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try{
            manager.authenticate(authentication);
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username or password !!");
        }
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
        return  userService.createUser(user);
    }

}
