package com.parag.lifetracker.controller;

import com.parag.lifetracker.dto.WeeklyReviewRequest;
import com.parag.lifetracker.dto.WeeklyReviewResponse;
import com.parag.lifetracker.service.WeeklyReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class WeeklyReviewController {

    private final WeeklyReviewService weeklyReviewService;

    @GetMapping
    public List<WeeklyReviewResponse> getReviews() {
        return weeklyReviewService.getReviews();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WeeklyReviewResponse createReview(@Valid @RequestBody WeeklyReviewRequest request) {
        return weeklyReviewService.createReview(request);
    }

    @PutMapping("/{id}")
    public WeeklyReviewResponse updateReview(@PathVariable Long id, @Valid @RequestBody WeeklyReviewRequest request) {
        return weeklyReviewService.updateReview(id, request);
    }
}
