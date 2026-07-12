package com.survey_backend_sky.repository;

import com.survey_backend_sky.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByResponseId(Long responseId);

}