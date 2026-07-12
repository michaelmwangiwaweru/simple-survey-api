package com.survey_backend_sky.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerRequest {

    @NotNull
    private Long questionId;

    private String answer;
}