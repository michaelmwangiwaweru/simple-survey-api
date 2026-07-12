package com.survey_backend_sky.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OptionRequest {

    @NotBlank
    private String optionText;

    @NotBlank
    private String optionValue;

    private Integer orderNumber = 1;

}