package com.parag.lifetracker.repository;

import com.parag.lifetracker.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByActiveTrueOrderByCreatedAtAsc();
    List<Habit> findAllByOrderByCreatedAtAsc();
}
