package com.survey_backend_sky.repository;

import com.survey_backend_sky.entity.Response;
import com.survey_backend_sky.entity.Survey;
import com.survey_backend_sky.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {

    List<Response> findAllByOrderBySubmittedAtDesc();

    List<Response> findBySurveyIdOrderBySubmittedAtDesc(Long surveyId);

    List<Response> findByUserId(Long userId);
    void deleteBySurveyId(Long surveyId);

    long countByUser(User user);

    boolean existsBySurveyAndUser(Survey survey, User user);

}