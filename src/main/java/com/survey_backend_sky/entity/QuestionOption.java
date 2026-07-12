package com.survey_backend_sky.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Display text shown to users.
     * Example: Male
     */
    @Column(nullable = false)
    private String optionText;

    /**
     * Value stored when selected.
     * Example: MALE
     */
    @Column(nullable = false)
    private String optionValue;

    @Column(nullable = false)
    @Builder.Default
    private Integer orderNumber = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

}