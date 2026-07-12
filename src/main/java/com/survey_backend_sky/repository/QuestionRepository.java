package com.survey_backend_sky.repository;

import com.survey_backend_sky.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findBySurveyIdOrderByOrderNumberAsc(Long surveyId);
    void deleteBySurveyId(Long surveyId);
}