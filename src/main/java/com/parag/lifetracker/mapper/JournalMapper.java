package com.parag.lifetracker.mapper;

import com.parag.lifetracker.dto.JournalRequest;
import com.parag.lifetracker.dto.JournalResponse;
import com.parag.lifetracker.entity.Journal;

public final class JournalMapper {
    private JournalMapper() {
    }

    public static Journal toEntity(JournalRequest request) {
        return Journal.builder()
                .entryDate(request.getEntryDate())
                .goodThings(request.getGoodThings())
                .improvement(request.getImprovement())
                .gratitude(request.getGratitude())
                .build();
    }

    public static JournalResponse toResponse(Journal journal) {
        return JournalResponse.builder()
                .id(journal.getId())
                .entryDate(journal.getEntryDate())
                .goodThings(journal.getGoodThings())
                .improvement(journal.getImprovement())
                .gratitude(journal.getGratitude())
                .build();
    }

    public static void updateEntity(Journal journal, JournalRequest request) {
        journal.setEntryDate(request.getEntryDate());
        journal.setGoodThings(request.getGoodThings());
        journal.setImprovement(request.getImprovement());
        journal.setGratitude(request.getGratitude());
    }
}
