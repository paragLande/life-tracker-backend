package com.parag.lifetracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JournalRequest {
    @NotNull(message = "entryDate is required")
    private LocalDate entryDate;

    private String goodThings;
    private String improvement;
    private String gratitude;
}
