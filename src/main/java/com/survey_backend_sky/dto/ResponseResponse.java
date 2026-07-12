package com.survey_backend_sky.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseResponse {

    private Long id;

    private Long surveyId;

    private String surveyTitle;

    private String submittedBy;
    private String submittedByEmail;
    private LocalDateTime submittedAt;

}