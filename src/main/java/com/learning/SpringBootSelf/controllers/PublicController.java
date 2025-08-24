package com.learning.SpringBootSelf.controllers;


import com.learning.SpringBootSelf.entaties.User;
import com.learning.SpringBootSelf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService service;


    @PostMapping("/add")
    public ResponseEntity<?> addOne(@RequestBody User user){
        try{
            service.saveEntry(user);
            return new ResponseEntity<>("Record saved", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Problem during record insertion", HttpStatus.BAD_REQUEST);
        }
    }
}
