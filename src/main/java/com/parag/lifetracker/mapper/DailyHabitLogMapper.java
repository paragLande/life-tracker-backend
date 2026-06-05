package com.parag.lifetracker.mapper;

import com.parag.lifetracker.dto.DailyHabitLogResponse;
import com.parag.lifetracker.entity.DailyHabitLog;

public final class DailyHabitLogMapper {
    private DailyHabitLogMapper() {
    }

    public static DailyHabitLogResponse toResponse(DailyHabitLog log) {
        return DailyHabitLogResponse.builder()
                .id(log.getId())
                .habitId(log.getHabit().getId())
                .habitName(log.getHabit().getName())
                .category(log.getHabit().getCategory())
                .date(log.getDate())
                .completed(log.getCompleted())
                .completedAt(log.getCompletedAt())
                .build();
    }
}
