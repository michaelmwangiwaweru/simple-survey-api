package com.survey_backend_sky.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SurveyRequest {

    @NotBlank
    private String title;

    private String description;

}