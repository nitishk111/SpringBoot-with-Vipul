package com.learning.SpringBootSelf.services;

import com.learning.SpringBootSelf.entaties.JournalEntry;
import com.learning.SpringBootSelf.entaties.User;
import com.learning.SpringBootSelf.repository.JournalEntryRepo;
import com.learning.SpringBootSelf.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalService {

    @Autowired
    JournalEntryRepo entryRepo;

    @Autowired
    UserRepo userRepo;

    private static final Logger logger= LoggerFactory.getLogger(JournalService.class);

    public void saveEntry(JournalEntry entry, String userName){
        User user= userRepo.findByUserName(userName);
        entry.setUser(user);
        entryRepo.save(entry);
        logger.info("Saved");
    }

    public List<JournalEntry> getAllRecordOfUser(String userName){

        User user= userRepo.findByUserName(userName);
        if (user==null) return null;

        return entryRepo.findByUserUserID(user.getUserID());
    }

    public JournalEntry getById(long id){
        return entryRepo.findById(id).orElseGet(()->null);
    }

    public void deleteEntry(long id){
        entryRepo.deleteById(id);
    }

    public JournalEntry updateEntry(long id, JournalEntry newEntry){

        JournalEntry oldEntry= getById(id);
        if(oldEntry !=null) {
            oldEntry.setJournalTitle(newEntry.getJournalTitle() != null && !newEntry.getJournalTitle().equals("") ? newEntry.getJournalTitle() : oldEntry.getJournalTitle());
            oldEntry.setStoryContent(newEntry.getStoryContent() != null && !newEntry.getStoryContent().equals(("")) ? newEntry.getStoryContent() : oldEntry.getStoryContent());
            entryRepo.save(oldEntry);
        }
        return oldEntry;
    }
}

