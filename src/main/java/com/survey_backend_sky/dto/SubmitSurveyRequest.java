package com.survey_backend_sky.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitSurveyRequest {

    @Valid
    @NotEmpty
    private List<AnswerRequest> answers;

}