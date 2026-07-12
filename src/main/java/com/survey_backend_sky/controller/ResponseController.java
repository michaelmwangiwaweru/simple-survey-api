package com.survey_backend_sky.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey_backend_sky.dto.ResponseDetailsResponse;
import com.survey_backend_sky.dto.ResponseResponse;
import com.survey_backend_sky.dto.SubmitSurveyRequest;
import com.survey_backend_sky.service.ResponseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ResponseController {

    private final ResponseService responseService;

    private final ObjectMapper objectMapper;

    /**
     * Submit Survey (Supports File Upload)
     */
    @PostMapping(
            value = "/surveys/{surveyId}/submit",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ResponseResponse> submitSurvey(

            @PathVariable Long surveyId,

            @RequestPart("request")
            String requestJson,

            @RequestPart(value = "files", required = false)
            List<MultipartFile> files,

            Authentication authentication

    ) throws IOException {

        SubmitSurveyRequest request =
                objectMapper.readValue(requestJson, SubmitSurveyRequest.class);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        responseService.submitSurvey(
                                surveyId,
                                request,
                                files,
                                authentication
                        )
                );
    }

    /**
     * Admin - View ALL Responses
     */
    @GetMapping("/responses")
    public ResponseEntity<List<ResponseResponse>> getAllResponses() {

        return ResponseEntity.ok(
                responseService.getAllResponses()
        );
    }

    /**
     * Admin - Survey Responses
     */
    @GetMapping("/surveys/{surveyId}/responses")
    public ResponseEntity<List<ResponseResponse>> getSurveyResponses(
            @PathVariable Long surveyId
    ) {

        return ResponseEntity.ok(
                responseService.getSurveyResponses(surveyId)
        );
    }

    /**
     * Admin - One Response
     */
    @GetMapping("/responses/{id}")
    public ResponseEntity<ResponseDetailsResponse> getResponse(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                responseService.getResponse(id)
        );
    }

    /**
     * Logged-in User Responses
     */
    @GetMapping("/responses/my")
    public ResponseEntity<List<ResponseResponse>> getMyResponses(
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                responseService.getMyResponses(authentication)
        );
    }

}