package com.survey_backend_sky.controller;

import com.survey_backend_sky.dto.DashboardResponse;
import com.survey_backend_sky.dto.SurveyRequest;
import com.survey_backend_sky.dto.SurveyResponse;
import com.survey_backend_sky.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SurveyController {

    private final SurveyService surveyService;


    @PostMapping
    public ResponseEntity<SurveyResponse> createSurvey(
            @Valid @RequestBody SurveyRequest request,
            Authentication authentication
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(surveyService.createSurvey(request, authentication));
    }


    @GetMapping
    public ResponseEntity<List<SurveyResponse>> getAllSurveys() {

        return ResponseEntity.ok(
                surveyService.getAllSurveys()
        );
    }


    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getDashboard(
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                surveyService.getDashboard(authentication)
        );

    }




    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponse> getSurvey(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                surveyService.getSurvey(id)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponse> updateSurvey(
            @PathVariable Long id,
            @Valid @RequestBody SurveyRequest request,
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                surveyService.updateSurvey(id, request, authentication)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(
            @PathVariable Long id,
            Authentication authentication
    ) {

        surveyService.deleteSurvey(id, authentication);

        return ResponseEntity.noContent().build();
    }

}