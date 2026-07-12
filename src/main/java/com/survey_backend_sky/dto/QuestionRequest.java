package com.survey_backend_sky.dto;

import com.survey_backend_sky.entity.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionRequest {

    @NotBlank
    private String questionText;

    @NotNull
    private QuestionType questionType;

    private Boolean required = false;

    private Integer orderNumber = 1;

    // NEW
    private List<OptionRequest> options = new ArrayList<>();

}