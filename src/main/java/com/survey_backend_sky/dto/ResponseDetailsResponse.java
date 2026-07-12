package com.survey_backend_sky.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ResponseDetailsResponse {

    private Long id;

    private Long surveyId;

    private String surveyTitle;

    private String submittedBy;

    private LocalDateTime submittedAt;

    private List<AnswerDto> answers;

}