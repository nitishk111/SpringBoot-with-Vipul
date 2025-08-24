package com.learning.SpringBootSelf.services;

import com.learning.SpringBootSelf.entaties.JournalEntry;
import com.learning.SpringBootSelf.entaties.User;
import com.learning.SpringBootSelf.repository.JournalEntryRepo;
import com.learning.SpringBootSelf.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {


        @Autowired
        UserRepo userRepo;

        @Autowired
        JournalEntryRepo journalRepo;

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        public boolean saveEntry(User user){
            try{
                user.setPassword(encoder.encode(user.getPassword()));
                userRepo.save(user);
                return true;
            } catch (Exception e) {
                log.error("Error for {} :", user.getUserName(), e.getMessage());
                log.warn("User already exists");
                return false;
            }

        }

    public User updateEntry(String userName, User newUser){

            User oldUser= userRepo.findByUserName(userName);
            if(oldUser !=null) {
                oldUser.setUserName(newUser.getUserName() != null && !newUser.getUserName().equals("") ? newUser.getUserName() : oldUser.getUserName());
                oldUser.setPassword(newUser.getPassword() != null && !newUser.getPassword().equals(("")) ? newUser.getPassword() : oldUser.getPassword());
                userRepo.save(oldUser);
            }
            return oldUser;
        }

        public void deleteEntry(String userName){

            User user= userRepo.findByUserName(userName);
            List<JournalEntry> journals= journalRepo.findByUserUserID(user.getUserID());

            if(journals !=null && journals.size()!=0){
                for(JournalEntry j:journals){
                    journalRepo.deleteById(j.getJournalId());
                }

            }
            userRepo.deleteById(user.getUserID());
        }

//        public List<User> getAll(){
//            return userRepo.findAll();
//        }
//
//        public User getById(long id){
//            return userRepo.findById(id).orElseGet(()->null);
//        }
//
//        public void deleteEntry(long id){
//            List<JournalEntry> journals= journalRepo.findByUserUserID(id);
//            if(journals !=null && journals.size()!=0){
//                for(JournalEntry j:journals){
//                    journalRepo.deleteById(j.getJournalId());
//                }
//                userRepo.deleteById(id);
//            }
//
//        }
//
//        public User updateEntry(long id, User newUser){
//
//            User oldUser= getById(id);
//            if(oldUser !=null) {
//                oldUser.setUserName(newUser.getUserName() != null && !newUser.getUserName().equals("") ? newUser.getUserName() : oldUser.getUserName());
//                oldUser.setPassword(newUser.getPassword() != null && !newUser.getPassword().equals(("")) ? newUser.getPassword() : oldUser.getPassword());
//                userRepo.save(oldUser);
//            }
//            return oldUser;
//        }

}
