package com.parag.lifetracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "journals")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private LocalDate entryDate;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String goodThings;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String improvement;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String gratitude;
}
