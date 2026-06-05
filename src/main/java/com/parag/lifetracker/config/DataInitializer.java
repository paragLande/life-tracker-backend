package com.parag.lifetracker.config;

import com.parag.lifetracker.entity.Habit;
import com.parag.lifetracker.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final HabitRepository habitRepository;

    @Bean
    CommandLineRunner seedDefaultHabits() {
        return args -> {
            if (habitRepository.count() > 0) {
                return;
            }
            List<Habit> defaults = List.of(
                    habit("Workout", "Health"),
                    habit("Growth Hour", "Career"),
                    habit("Reading", "Growth"),
                    habit("Wife Time", "Relationship"),
                    habit("Drive", "Adventure"),
                    habit("Sleep Before 11", "Health")
            );
            habitRepository.saveAll(defaults);
        };
    }

    private Habit habit(String name, String category) {
        return Habit.builder()
                .name(name)
                .category(category)
                .active(true)
                .build();
    }
}
