package com.parag.lifetracker.controller;

import com.parag.lifetracker.dto.CheckHabitRequest;
import com.parag.lifetracker.dto.DailyHabitLogResponse;
import com.parag.lifetracker.service.DailyLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class DailyLogController {

    private final DailyLogService dailyLogService;

    @GetMapping("/today")
    public List<DailyHabitLogResponse> getTodayLogs() {
        return dailyLogService.getTodayLogs();
    }

    @PostMapping("/check")
    public DailyHabitLogResponse checkHabit(@Valid @RequestBody CheckHabitRequest request) {
        return dailyLogService.checkHabit(request);
    }

    @PostMapping("/uncheck")
    public DailyHabitLogResponse uncheckHabit(@Valid @RequestBody CheckHabitRequest request) {
        return dailyLogService.uncheckHabit(request);
    }

    @GetMapping("/date/{date}")
    public List<DailyHabitLogResponse> getLogsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return dailyLogService.getLogsForDate(date);
    }
}
