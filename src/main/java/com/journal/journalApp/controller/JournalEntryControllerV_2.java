package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV_2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAllEntries();
    }

    @PostMapping
    public JournalEntry createEntries(@RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveJournalEntry(entry);
        return entry;
    }

    @GetMapping("id/{journalId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId journalId){
       return journalEntryService.getEntryById(journalId).orElse(null);

    }

    @DeleteMapping("id/{journalId}")
    public ObjectId deleteJournalEntryById(@PathVariable ObjectId journalId){
        journalEntryService.deleteById(journalId);
        return journalId;
    }

    @PutMapping("id/{journalId}")
    public ObjectId updateEntries(@PathVariable ObjectId journalId,@RequestBody JournalEntry entry){
        JournalEntry oldEntry=journalEntryService.getEntryById(journalId).orElse(null);
        if(oldEntry!=null){
            oldEntry.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")?entry.getTitle(): oldEntry.getTitle());
            oldEntry.setDescription(entry.getDescription()!=null && !entry.getDescription().equals("")?entry.getDescription(): oldEntry.getDescription());
        }
        journalEntryService.saveJournalEntry(oldEntry);
        return journalId;
    }
}
