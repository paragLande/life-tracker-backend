package com.parag.lifetracker.repository;

import com.parag.lifetracker.entity.WeeklyReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeeklyReviewRepository extends JpaRepository<WeeklyReview, Long> {
    Optional<WeeklyReview> findByReviewDate(LocalDate reviewDate);
    List<WeeklyReview> findTop12ByOrderByReviewDateAsc();
}
