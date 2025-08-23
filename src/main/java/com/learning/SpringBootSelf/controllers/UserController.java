package com.learning.SpringBootSelf.controllers;

import com.learning.SpringBootSelf.entaties.User;
import com.learning.SpringBootSelf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService service;

    @GetMapping("/fetch")
    public ResponseEntity<?> getAll(){
        List<User> rec= service.getAll();
        if(rec ==null || rec.size()==0){
            return new ResponseEntity<>("No Record Available", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rec, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOne(@RequestBody User user){
        try{
            service.saveEntry(user);
            return new ResponseEntity<>("Record saved", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Problem during record insertion", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        User user= service.getById(id);
        if(user==null){
            return new ResponseEntity<>("No Record Available of id= "+id,HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id){
        User user= service.getById(id);
        if(user != null){
            service.deleteEntry(id);
            return new ResponseEntity<>("Record with id= "+id +" deleted", HttpStatus.OK);
        }else
            return new ResponseEntity<>("No Record Available of id= "+id,HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable("id") long id, @RequestBody User user){
        try {
            service.updateEntry(id, user);
            return new ResponseEntity<>("Record with id= " + id + " updated.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No record available with id= "+id, HttpStatus.NOT_FOUND);
        }
    }
}
