package com.survey_backend_sky.controller;

import com.survey_backend_sky.dto.QuestionRequest;
import com.survey_backend_sky.dto.QuestionResponse;
import com.survey_backend_sky.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/api/surveys/{surveyId}/questions")
    public ResponseEntity<QuestionResponse> createQuestion(
            @PathVariable Long surveyId,
            @Valid @RequestBody QuestionRequest request
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(questionService.createQuestion(surveyId, request));

    }

    @GetMapping("/api/surveys/{surveyId}/questions")
    public ResponseEntity<List<QuestionResponse>> getSurveyQuestions(
            @PathVariable Long surveyId
    ) {

        return ResponseEntity.ok(
                questionService.getSurveyQuestions(surveyId)
        );

    }

    @GetMapping("/api/questions/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                questionService.getQuestion(id)
        );

    }

    @PutMapping("/api/questions/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionRequest request
    ) {

        return ResponseEntity.ok(
                questionService.updateQuestion(id, request)
        );

    }

    @DeleteMapping("/api/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(
            @PathVariable Long id
    ) {

        questionService.deleteQuestion(id);

        return ResponseEntity.noContent().build();

    }

}