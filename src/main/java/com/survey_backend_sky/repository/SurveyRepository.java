package com.survey_backend_sky.repository;

import com.survey_backend_sky.entity.Survey;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Override
    @EntityGraph(attributePaths = {"createdBy"})
    List<Survey> findAll();

    @Override
    @EntityGraph(attributePaths = {"createdBy"})
    Optional<Survey> findById(Long id);

}