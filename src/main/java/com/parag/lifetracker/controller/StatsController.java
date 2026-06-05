package com.parag.lifetracker.controller;

import com.parag.lifetracker.dto.StatsResponse;
import com.parag.lifetracker.dto.TodayStatsResponse;
import com.parag.lifetracker.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/today")
    public TodayStatsResponse getTodayStats() {
        return statsService.getTodayStats();
    }

    @GetMapping("/week")
    public StatsResponse getWeekStats() {
        return statsService.getWeekStats();
    }

    @GetMapping("/month")
    public StatsResponse getMonthStats() {
        return statsService.getMonthStats();
    }
}
