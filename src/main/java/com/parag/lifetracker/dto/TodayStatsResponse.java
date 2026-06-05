package com.parag.lifetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TodayStatsResponse {
    private StatsResponse stats;
    private int streak;
    private List<DailyHabitLogResponse> logs;
}
