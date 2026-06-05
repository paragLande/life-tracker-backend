package com.parag.lifetracker.mapper;

import com.parag.lifetracker.dto.HabitRequest;
import com.parag.lifetracker.dto.HabitResponse;
import com.parag.lifetracker.entity.Habit;

public final class HabitMapper {
    private HabitMapper() {
    }

    public static Habit toEntity(HabitRequest request) {
        return Habit.builder()
                .name(request.getName().trim())
                .category(request.getCategory().trim())
                .active(request.getActive() == null || request.getActive())
                .build();
    }

    public static HabitResponse toResponse(Habit habit) {
        return HabitResponse.builder()
                .id(habit.getId())
                .name(habit.getName())
                .category(habit.getCategory())
                .active(habit.getActive())
                .createdAt(habit.getCreatedAt())
                .build();
    }

    public static void updateEntity(Habit habit, HabitRequest request) {
        habit.setName(request.getName().trim());
        habit.setCategory(request.getCategory().trim());
        if (request.getActive() != null) {
            habit.setActive(request.getActive());
        }
    }
}
