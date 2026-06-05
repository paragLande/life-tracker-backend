package com.parag.lifetracker.repository;

import com.parag.lifetracker.entity.DailyHabitLog;
import com.parag.lifetracker.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyHabitLogRepository extends JpaRepository<DailyHabitLog, Long> {
    Optional<DailyHabitLog> findByHabitAndDate(Habit habit, LocalDate date);
    List<DailyHabitLog> findByDateOrderByHabitCreatedAtAsc(LocalDate date);
    List<DailyHabitLog> findByDateBetween(LocalDate startDate, LocalDate endDate);
    long countByDateAndCompletedTrue(LocalDate date);
    long countByDateBetweenAndCompletedTrue(LocalDate startDate, LocalDate endDate);
}
