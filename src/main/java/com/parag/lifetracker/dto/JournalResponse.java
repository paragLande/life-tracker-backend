package com.parag.lifetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JournalResponse {
    private Long id;
    private LocalDate entryDate;
    private String goodThings;
    private String improvement;
    private String gratitude;
}
