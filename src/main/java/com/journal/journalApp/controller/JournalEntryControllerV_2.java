package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return null;
    }

    @PostMapping
    public String createEntries(@RequestBody JournalEntry entry){
        journalEntryService.saveJournalEntry(entry);
        return entry.getId();
    }

    @GetMapping("id/{journalId}")
    public JournalEntry getJournalEntryById(@PathVariable String journalId){
        return null;
    }

    @DeleteMapping("id/{journalId}")
    public String deleteJournalEntryById(@PathVariable String journalId){
        return journalId;
    }

    @PutMapping("id/{journalId}")
    public String updateEntries(@PathVariable String journalId,@RequestBody JournalEntry entry){
        return journalId;
    }
}
