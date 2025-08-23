package com.learning.SpringBootSelf.repository;

import com.learning.SpringBootSelf.entaties.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalEntryRepo extends JpaRepository<JournalEntry, Long> {

    public List<JournalEntry> findByUserUserID(long userId);

//    public  void deleteByUserUserId(long userId);
}
