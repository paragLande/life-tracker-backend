package com.parag.lifetracker.mapper;

import com.parag.lifetracker.dto.WeeklyReviewRequest;
import com.parag.lifetracker.dto.WeeklyReviewResponse;
import com.parag.lifetracker.entity.WeeklyReview;

public final class WeeklyReviewMapper {
    private WeeklyReviewMapper() {
    }

    public static WeeklyReview toEntity(WeeklyReviewRequest request) {
        return WeeklyReview.builder()
                .reviewDate(request.getReviewDate())
                .healthScore(request.getHealthScore())
                .careerScore(request.getCareerScore())
                .relationshipScore(request.getRelationshipScore())
                .financeScore(request.getFinanceScore())
                .adventureScore(request.getAdventureScore())
                .build();
    }

    public static WeeklyReviewResponse toResponse(WeeklyReview review) {
        return WeeklyReviewResponse.builder()
                .id(review.getId())
                .reviewDate(review.getReviewDate())
                .healthScore(review.getHealthScore())
                .careerScore(review.getCareerScore())
                .relationshipScore(review.getRelationshipScore())
                .financeScore(review.getFinanceScore())
                .adventureScore(review.getAdventureScore())
                .build();
    }

    public static void updateEntity(WeeklyReview review, WeeklyReviewRequest request) {
        review.setReviewDate(request.getReviewDate());
        review.setHealthScore(request.getHealthScore());
        review.setCareerScore(request.getCareerScore());
        review.setRelationshipScore(request.getRelationshipScore());
        review.setFinanceScore(request.getFinanceScore());
        review.setAdventureScore(request.getAdventureScore());
    }
}
