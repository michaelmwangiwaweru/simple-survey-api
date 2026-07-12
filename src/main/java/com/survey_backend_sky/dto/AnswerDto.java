package com.survey_backend_sky.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {

    private Long questionId;

    private String question;

    private String answer;

    private boolean file;

    private String fileName;
}