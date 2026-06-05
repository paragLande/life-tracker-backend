package com.parag.lifetracker.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WeeklyReviewRequest {
    @NotNull(message = "reviewDate is required")
    private LocalDate reviewDate;

    @Min(0) @Max(10)
    private Integer healthScore;
    @Min(0) @Max(10)
    private Integer careerScore;
    @Min(0) @Max(10)
    private Integer relationshipScore;
    @Min(0) @Max(10)
    private Integer financeScore;
    @Min(0) @Max(10)
    private Integer adventureScore;
}
