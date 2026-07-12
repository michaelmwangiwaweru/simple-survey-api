package com.survey_backend_sky.repository;

import com.survey_backend_sky.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionOptionRepository
        extends JpaRepository<QuestionOption, Long> {

    List<QuestionOption> findByQuestionIdOrderByOrderNumber(Long questionId);

    void deleteByQuestionId(Long questionId);

}