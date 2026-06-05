package com.parag.lifetracker.service;

import com.parag.lifetracker.dto.HabitRequest;
import com.parag.lifetracker.dto.HabitResponse;
import com.parag.lifetracker.entity.Habit;
import com.parag.lifetracker.exception.ResourceNotFoundException;
import com.parag.lifetracker.mapper.HabitMapper;
import com.parag.lifetracker.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;

    @Transactional(readOnly = true)
    public List<HabitResponse> getAllHabits() {
        return habitRepository.findAllByOrderByCreatedAtAsc().stream()
                .map(HabitMapper::toResponse)
                .toList();
    }

    @Transactional
    public HabitResponse createHabit(HabitRequest request) {
        Habit habit = habitRepository.save(HabitMapper.toEntity(request));
        return HabitMapper.toResponse(habit);
    }

    @Transactional
    public HabitResponse updateHabit(Long id, HabitRequest request) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id " + id));
        HabitMapper.updateEntity(habit, request);
        return HabitMapper.toResponse(habitRepository.save(habit));
    }

    @Transactional
    public void deleteHabit(Long id) {
        if (!habitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Habit not found with id " + id);
        }
        habitRepository.deleteById(id);
    }
}
