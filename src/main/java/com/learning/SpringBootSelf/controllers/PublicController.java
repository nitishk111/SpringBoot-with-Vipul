package com.learning.SpringBootSelf.controllers;


import com.learning.SpringBootSelf.Utils.JwtUtil;
import com.learning.SpringBootSelf.entaties.User;
import com.learning.SpringBootSelf.services.UserDetailServiceImpl;
import com.learning.SpringBootSelf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            service.saveEntry(user);
            return new ResponseEntity<>("Record saved", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Problem during record insertion", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody User user) {

        try {
            authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())));

            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());

            String token= jwtUtil.generateToken(userDetails.getUsername());

            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);

        } catch (Exception e) {

            return new ResponseEntity<>("Your Password or username is incorrect", HttpStatus.UNAUTHORIZED);
        }

    }

}
