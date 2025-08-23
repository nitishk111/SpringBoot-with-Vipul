package com.learning.SpringBootSelf.controllers;

import com.learning.SpringBootSelf.entaties.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController_noDB {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("/entries")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping("/addEntry")
    public String addOne(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getJournalId(), entry);
        return "Entry Added";
    }

    @GetMapping("/entry/{id}")
    public JournalEntry getById(@PathVariable("id") long id){
        return journalEntries.get(id);
    }
}
