package com.parag.lifetracker.controller;

import com.parag.lifetracker.dto.HabitRequest;
import com.parag.lifetracker.dto.HabitResponse;
import com.parag.lifetracker.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping
    public List<HabitResponse> getHabits() {
        return habitService.getAllHabits();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HabitResponse createHabit(@Valid @RequestBody HabitRequest request) {
        return habitService.createHabit(request);
    }

    @PutMapping("/{id}")
    public HabitResponse updateHabit(@PathVariable Long id, @Valid @RequestBody HabitRequest request) {
        return habitService.updateHabit(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
    }
}
