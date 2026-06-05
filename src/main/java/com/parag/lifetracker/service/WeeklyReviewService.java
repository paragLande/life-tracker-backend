package com.parag.lifetracker.service;

import com.parag.lifetracker.dto.WeeklyReviewRequest;
import com.parag.lifetracker.dto.WeeklyReviewResponse;
import com.parag.lifetracker.entity.WeeklyReview;
import com.parag.lifetracker.exception.ResourceNotFoundException;
import com.parag.lifetracker.mapper.WeeklyReviewMapper;
import com.parag.lifetracker.repository.WeeklyReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeeklyReviewService {

    private final WeeklyReviewRepository weeklyReviewRepository;

    @Transactional(readOnly = true)
    public List<WeeklyReviewResponse> getReviews() {
        return weeklyReviewRepository.findTop12ByOrderByReviewDateAsc().stream()
                .map(WeeklyReviewMapper::toResponse)
                .toList();
    }

    @Transactional
    public WeeklyReviewResponse createReview(WeeklyReviewRequest request) {
        WeeklyReview review = weeklyReviewRepository.findByReviewDate(request.getReviewDate())
                .orElseGet(() -> WeeklyReviewMapper.toEntity(request));
        WeeklyReviewMapper.updateEntity(review, request);
        return WeeklyReviewMapper.toResponse(weeklyReviewRepository.save(review));
    }

    @Transactional
    public WeeklyReviewResponse updateReview(Long id, WeeklyReviewRequest request) {
        WeeklyReview review = weeklyReviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Weekly review not found with id " + id));
        WeeklyReviewMapper.updateEntity(review, request);
        return WeeklyReviewMapper.toResponse(weeklyReviewRepository.save(review));
    }
}
