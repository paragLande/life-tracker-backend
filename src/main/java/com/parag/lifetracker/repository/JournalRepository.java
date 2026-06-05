package com.parag.lifetracker.repository;

import com.parag.lifetracker.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    Optional<Journal> findByEntryDate(LocalDate entryDate);
    List<Journal> findTop30ByOrderByEntryDateDesc();
}
