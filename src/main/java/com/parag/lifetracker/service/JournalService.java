package com.parag.lifetracker.service;

import com.parag.lifetracker.dto.JournalRequest;
import com.parag.lifetracker.dto.JournalResponse;
import com.parag.lifetracker.entity.Journal;
import com.parag.lifetracker.exception.BadRequestException;
import com.parag.lifetracker.exception.ResourceNotFoundException;
import com.parag.lifetracker.mapper.JournalMapper;
import com.parag.lifetracker.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;

    @Transactional(readOnly = true)
    public JournalResponse getJournalByDate(LocalDate date) {
        return journalRepository.findByEntryDate(date)
                .map(JournalMapper::toResponse)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<JournalResponse> getRecentJournals() {
        return journalRepository.findTop30ByOrderByEntryDateDesc().stream()
                .map(JournalMapper::toResponse)
                .toList();
    }

    @Transactional
    public JournalResponse createJournal(JournalRequest request) {
        journalRepository.findByEntryDate(request.getEntryDate()).ifPresent(existing -> {
            throw new BadRequestException("Journal already exists for " + request.getEntryDate());
        });
        return JournalMapper.toResponse(journalRepository.save(JournalMapper.toEntity(request)));
    }

    @Transactional
    public JournalResponse updateJournal(Long id, JournalRequest request) {
        Journal journal = journalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Journal not found with id " + id));
        JournalMapper.updateEntity(journal, request);
        return JournalMapper.toResponse(journalRepository.save(journal));
    }
}
