package com.parag.lifetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WeeklyReviewResponse {
    private Long id;
    private LocalDate reviewDate;
    private Integer healthScore;
    private Integer careerScore;
    private Integer relationshipScore;
    private Integer financeScore;
    private Integer adventureScore;
}
