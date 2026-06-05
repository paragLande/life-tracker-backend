package com.parag.lifetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HabitResponse {
    private Long id;
    private String name;
    private String category;
    private Boolean active;
    private LocalDateTime createdAt;
}
