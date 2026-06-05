package com.parag.lifetracker.service;

import com.parag.lifetracker.dto.DailyHabitLogResponse;
import com.parag.lifetracker.dto.StatsResponse;
import com.parag.lifetracker.dto.TodayStatsResponse;
import com.parag.lifetracker.entity.DailyHabitLog;
import com.parag.lifetracker.entity.Habit;
import com.parag.lifetracker.mapper.DailyHabitLogMapper;
import com.parag.lifetracker.repository.DailyHabitLogRepository;
import com.parag.lifetracker.repository.HabitRepository;
import com.parag.lifetracker.util.DateRangeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final DailyLogService dailyLogService;
    private final DailyHabitLogRepository dailyHabitLogRepository;
    private final HabitRepository habitRepository;

    @Transactional
    public TodayStatsResponse getTodayStats() {
        LocalDate today = LocalDate.now();
        dailyLogService.ensureLogsForDate(today);
        List<DailyHabitLog> logs = dailyHabitLogRepository.findByDateOrderByHabitCreatedAtAsc(today);
        long completed = logs.stream().filter(log -> Boolean.TRUE.equals(log.getCompleted())).count();
        StatsResponse stats = buildStats("Today", completed, logs.size());
        List<DailyHabitLogResponse> responses = logs.stream().map(DailyHabitLogMapper::toResponse).toList();
        return TodayStatsResponse.builder()
                .stats(stats)
                .streak(calculateStreak())
                .logs(responses)
                .build();
    }

    @Transactional
    public StatsResponse getWeekStats() {
        LocalDate today = LocalDate.now();
        LocalDate start = DateRangeUtils.startOfCurrentWeek(today);
        return calculatePeriodStats("This Week", start, today);
    }

    @Transactional
    public StatsResponse getMonthStats() {
        LocalDate today = LocalDate.now();
        LocalDate start = DateRangeUtils.startOfCurrentMonth(today);
        return calculatePeriodStats("This Month", start, today);
    }

    private StatsResponse calculatePeriodStats(String label, LocalDate start, LocalDate end) {
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            dailyLogService.ensureLogsForDate(date);
        }
        long completed = dailyHabitLogRepository.countByDateBetweenAndCompletedTrue(start, end);
        long days = ChronoUnit.DAYS.between(start, end) + 1;
        long total = habitRepository.findByActiveTrueOrderByCreatedAtAsc().size() * days;
        return buildStats(label, completed, total);
    }

    private StatsResponse buildStats(String label, long completed, long total) {
        double score = total == 0 ? 0 : Math.round((completed * 10000.0) / total) / 100.0;
        return StatsResponse.builder()
                .label(label)
                .completed(completed)
                .total(total)
                .score(score)
                .build();
    }

    private int calculateStreak() {
        List<Habit> activeHabits = habitRepository.findByActiveTrueOrderByCreatedAtAsc();
        if (activeHabits.isEmpty()) {
            return 0;
        }
        int streak = 0;
        LocalDate cursor = LocalDate.now();
        while (streak < 365) {
            dailyLogService.ensureLogsForDate(cursor);
            List<DailyHabitLog> logs = dailyHabitLogRepository.findByDateOrderByHabitCreatedAtAsc(cursor);
            boolean perfectDay = logs.size() == activeHabits.size() && logs.stream().allMatch(log -> Boolean.TRUE.equals(log.getCompleted()));
            if (!perfectDay) {
                break;
            }
            streak++;
            cursor = cursor.minusDays(1);
        }
        return streak;
    }
}
