package com.survey_backend_sky.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionResponse {

    private Long id;

    private String optionText;

    private String optionValue;

    private Integer orderNumber;

}