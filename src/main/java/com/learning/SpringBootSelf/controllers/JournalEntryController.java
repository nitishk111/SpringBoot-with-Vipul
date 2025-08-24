package com.learning.SpringBootSelf.controllers;

import com.learning.SpringBootSelf.entaties.JournalEntry;
import com.learning.SpringBootSelf.services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalService service;

    @GetMapping("/get")
    public ResponseEntity<?> getAllRecordOfUser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String userName= auth.getName();
        List<JournalEntry> rec= service.getAllRecordOfUser(userName);
        if(rec ==null || rec.size()==0){
            return new ResponseEntity<>("No Record Available",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rec, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOne(@RequestBody JournalEntry entry){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String userName= auth.getName();
        try{
                service.saveEntry(entry, userName);
                return new ResponseEntity<>("Record saved", HttpStatus.ACCEPTED);
            }catch (Exception e){
                return new ResponseEntity<>("Problem during record insertion", HttpStatus.BAD_REQUEST);
            }
    }

//    @GetMapping("/fetchById/{id}")
//    public ResponseEntity<?> getById(@PathVariable("id") long id){
//        JournalEntry entry= service.getById(id);
//        if(entry==null){
//            return new ResponseEntity<>("No Record Available of id= "+id,HttpStatus.NO_CONTENT);
//        }
//        else return new ResponseEntity<>(entry, HttpStatus.FOUND);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id){
        try{
            service.deleteEntry(id);
            return new ResponseEntity<>("Record with id= "+id +" deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No Record Available of id= "+id,HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable("id") long id, @RequestBody JournalEntry entry){
        try {
            service.updateEntry(id, entry);
            return new ResponseEntity<>("Record with id= " + id + " updated.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No record available with id= "+id, HttpStatus.NOT_FOUND);
        }
    }
}
