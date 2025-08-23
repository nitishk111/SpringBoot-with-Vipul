package com.learning.SpringBootSelf.services;

import com.learning.SpringBootSelf.entaties.JournalEntry;
import com.learning.SpringBootSelf.entaties.User;
import com.learning.SpringBootSelf.repository.JournalEntryRepo;
import com.learning.SpringBootSelf.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JournalEntryRepo journalRepo;



    public boolean saveEntry(User user){
        try{
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            log.error("Error for {} :", user.getUserName(), e.getMessage());
            log.warn("User already exists");
            return false;
        }

    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User getById(long id){
        return userRepo.findById(id).orElseGet(()->null);
    }

    public void deleteEntry(long id){
        List<JournalEntry> journals= journalRepo.findByUserUserID(id);
        if(journals !=null && journals.size()!=0){
            for(JournalEntry j:journals){
                journalRepo.deleteById(j.getJournalId());
            }
            userRepo.deleteById(id);
        }

    }

    public User updateEntry(long id, User newUser){

        User oldUser= getById(id);
        if(oldUser !=null) {
            oldUser.setUserName(newUser.getUserName() != null && !newUser.getUserName().equals("") ? newUser.getUserName() : oldUser.getUserName());
            oldUser.setPassword(newUser.getPassword() != null && !newUser.getPassword().equals(("")) ? newUser.getPassword() : oldUser.getPassword());
            userRepo.save(oldUser);
        }
        return oldUser;
    }
}
