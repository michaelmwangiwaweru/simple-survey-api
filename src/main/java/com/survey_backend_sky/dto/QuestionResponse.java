package com.survey_backend_sky.dto;

import com.survey_backend_sky.entity.QuestionType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionResponse {

    private Long id;

    private String questionText;

    private QuestionType questionType;

    private Boolean required;

    private Integer orderNumber;

    private Long surveyId;

    // For RADIO, CHECKBOX and DROPDOWN questions
    private List<OptionResponse> options;

}