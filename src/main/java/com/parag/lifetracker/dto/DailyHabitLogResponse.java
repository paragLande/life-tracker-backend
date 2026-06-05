package com.parag.lifetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class DailyHabitLogResponse {
    private Long id;
    private Long habitId;
    private String habitName;
    private String category;
    private LocalDate date;
    private Boolean completed;
    private LocalDateTime completedAt;
}
