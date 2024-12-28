package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long,JournalEntry> journalEntries=new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());

    }

    @PostMapping
    public long createEntries(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getId(), entry);
        return entry.getId();
    }

    @GetMapping("id/{journalId}")
    public JournalEntry getJournalEntryById(@PathVariable Long journalId){
        return journalEntries.get(journalId);

    }

    @DeleteMapping("id/{journalId}")
    public long deleteJournalEntryById(@PathVariable Long journalId){
        journalEntries.remove(journalId);
        return journalId;
    }

    @PutMapping("id/{journalId}")
    public long updateEntries(@PathVariable Long journalId,@RequestBody JournalEntry entry){
        journalEntries.put(journalId, entry);
        return journalId;
    }
}
