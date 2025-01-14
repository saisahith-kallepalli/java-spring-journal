package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV_2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll(){
        List<JournalEntry> allEntries = journalEntryService.getAllEntries();
        if(allEntries!=null && !allEntries.isEmpty()) {
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntries(@RequestBody JournalEntry entry){
        try {
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveJournalEntry(entry);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId journalId){
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(journalId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{journalId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId journalId){
        journalEntryService.deleteById(journalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> updateEntries(@PathVariable ObjectId journalId,@RequestBody JournalEntry entry){
        JournalEntry oldEntry=journalEntryService.getEntryById(journalId).orElse(null);
        if(oldEntry!=null){
            oldEntry.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")?entry.getTitle(): oldEntry.getTitle());
            oldEntry.setDescription(entry.getDescription()!=null && !entry.getDescription().equals("")?entry.getDescription(): oldEntry.getDescription());
            journalEntryService.saveJournalEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(oldEntry,HttpStatus.NOT_FOUND);

    }
}
