package com.parag.lifetracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HabitRequest {
    @NotBlank(message = "Habit name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    private Boolean active = true;
}
