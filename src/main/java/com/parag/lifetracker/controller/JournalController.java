package com.parag.lifetracker.controller;

import com.parag.lifetracker.dto.JournalRequest;
import com.parag.lifetracker.dto.JournalResponse;
import com.parag.lifetracker.service.JournalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @GetMapping
    public List<JournalResponse> getRecentJournals() {
        return journalService.getRecentJournals();
    }

    @GetMapping("/{date}")
    public JournalResponse getJournalByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return journalService.getJournalByDate(date);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JournalResponse createJournal(@Valid @RequestBody JournalRequest request) {
        return journalService.createJournal(request);
    }

    @PutMapping("/{id}")
    public JournalResponse updateJournal(@PathVariable Long id, @Valid @RequestBody JournalRequest request) {
        return journalService.updateJournal(id, request);
    }
}
