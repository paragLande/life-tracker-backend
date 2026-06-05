package com.parag.lifetracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CheckHabitRequest {
    @NotNull(message = "habitId is required")
    private Long habitId;

    private LocalDate date;
}
