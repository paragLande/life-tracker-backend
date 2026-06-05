package com.parag.lifetracker.service;

import com.parag.lifetracker.dto.CheckHabitRequest;
import com.parag.lifetracker.dto.DailyHabitLogResponse;
import com.parag.lifetracker.entity.DailyHabitLog;
import com.parag.lifetracker.entity.Habit;
import com.parag.lifetracker.exception.ResourceNotFoundException;
import com.parag.lifetracker.mapper.DailyHabitLogMapper;
import com.parag.lifetracker.repository.DailyHabitLogRepository;
import com.parag.lifetracker.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyLogService {

    private final DailyHabitLogRepository dailyHabitLogRepository;
    private final HabitRepository habitRepository;

    @Transactional
    public List<DailyHabitLogResponse> getTodayLogs() {
        return getLogsForDate(LocalDate.now());
    }

    @Transactional
    public List<DailyHabitLogResponse> getLogsForDate(LocalDate date) {
        ensureLogsForDate(date);
        return dailyHabitLogRepository.findByDateOrderByHabitCreatedAtAsc(date).stream()
                .map(DailyHabitLogMapper::toResponse)
                .toList();
    }

    @Transactional
    public DailyHabitLogResponse checkHabit(CheckHabitRequest request) {
        return setHabitCompletion(request, true);
    }

    @Transactional
    public DailyHabitLogResponse uncheckHabit(CheckHabitRequest request) {
        return setHabitCompletion(request, false);
    }

    public void ensureLogsForDate(LocalDate date) {
        List<Habit> activeHabits = habitRepository.findByActiveTrueOrderByCreatedAtAsc();
        for (Habit habit : activeHabits) {
            dailyHabitLogRepository.findByHabitAndDate(habit, date)
                    .orElseGet(() -> dailyHabitLogRepository.save(DailyHabitLog.builder()
                            .habit(habit)
                            .date(date)
                            .completed(false)
                            .build()));
        }
    }

    private DailyHabitLogResponse setHabitCompletion(CheckHabitRequest request, boolean completed) {
        LocalDate date = request.getDate() == null ? LocalDate.now() : request.getDate();
        Habit habit = habitRepository.findById(request.getHabitId())
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id " + request.getHabitId()));
        DailyHabitLog log = dailyHabitLogRepository.findByHabitAndDate(habit, date)
                .orElse(DailyHabitLog.builder()
                        .habit(habit)
                        .date(date)
                        .completed(false)
                        .build());
        log.setCompleted(completed);
        log.setCompletedAt(completed ? LocalDateTime.now() : null);
        return DailyHabitLogMapper.toResponse(dailyHabitLogRepository.save(log));
    }
}
