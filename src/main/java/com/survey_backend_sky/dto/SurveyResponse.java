package com.survey_backend_sky.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SurveyResponse {

    private Long id;

    private String title;

    private String description;

    private String createdBy;

    private LocalDateTime createdAt;

}