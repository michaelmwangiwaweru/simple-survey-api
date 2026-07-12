package com.survey_backend_sky.service;

import com.survey_backend_sky.dto.OptionRequest;
import com.survey_backend_sky.dto.OptionResponse;
import com.survey_backend_sky.dto.QuestionRequest;
import com.survey_backend_sky.dto.QuestionResponse;
import com.survey_backend_sky.entity.Question;
import com.survey_backend_sky.entity.QuestionOption;
import com.survey_backend_sky.entity.QuestionType;
import com.survey_backend_sky.entity.Survey;
import com.survey_backend_sky.repository.QuestionOptionRepository;
import com.survey_backend_sky.repository.QuestionRepository;
import com.survey_backend_sky.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final QuestionOptionRepository optionRepository;

    public QuestionResponse createQuestion(Long surveyId,
                                           QuestionRequest request) {

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() ->
                        new RuntimeException("Survey not found"));

        Question question = Question.builder()
                .questionText(request.getQuestionText())
                .questionType(request.getQuestionType())
                .required(request.getRequired())
                .orderNumber(request.getOrderNumber())
                .survey(survey)
                .build();

        question = questionRepository.save(question);

        saveOptions(question, request);

        return map(question);
    }

    @Transactional(readOnly = true)
    public List<QuestionResponse> getSurveyQuestions(Long surveyId) {

        return questionRepository
                .findBySurveyIdOrderByOrderNumberAsc(surveyId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Transactional(readOnly = true)
    public QuestionResponse getQuestion(Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Question not found"));

        return map(question);
    }

    public QuestionResponse updateQuestion(Long id,
                                           QuestionRequest request) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Question not found"));

        question.setQuestionText(request.getQuestionText());
        question.setQuestionType(request.getQuestionType());
        question.setRequired(request.getRequired());
        question.setOrderNumber(request.getOrderNumber());

        question = questionRepository.save(question);

        // Delete existing options
        optionRepository.deleteByQuestionId(question.getId());

        // Save new options
        saveOptions(question, request);

        return map(question);
    }

    public void deleteQuestion(Long id) {

        questionRepository.deleteById(id);

    }

    /**
     * Save options only for RADIO, CHECKBOX and DROPDOWN questions
     */
    private void saveOptions(Question question,
                             QuestionRequest request) {

        if (request.getOptions() == null ||
                request.getOptions().isEmpty()) {
            return;
        }

        if (question.getQuestionType() != QuestionType.RADIO &&
                question.getQuestionType() != QuestionType.CHECKBOX &&
                question.getQuestionType() != QuestionType.DROPDOWN) {
            return;
        }

        for (OptionRequest optionRequest : request.getOptions()) {

            QuestionOption option = QuestionOption.builder()
                    .question(question)
                    .optionText(optionRequest.getOptionText())
                    .optionValue(optionRequest.getOptionValue())
                    .orderNumber(
                            optionRequest.getOrderNumber() == null
                                    ? 1
                                    : optionRequest.getOrderNumber()
                    )
                    .build();

            optionRepository.save(option);
        }
    }

    /**
     * Convert Entity to DTO
     */
    private QuestionResponse map(Question question) {

        List<OptionResponse> options = Collections.emptyList();

        if (question.getQuestionType() == QuestionType.RADIO ||
                question.getQuestionType() == QuestionType.CHECKBOX ||
                question.getQuestionType() == QuestionType.DROPDOWN) {

            options = optionRepository
                    .findByQuestionIdOrderByOrderNumber(question.getId())
                    .stream()
                    .map(option -> OptionResponse.builder()
                            .id(option.getId())
                            .optionText(option.getOptionText())
                            .optionValue(option.getOptionValue())
                            .orderNumber(option.getOrderNumber())
                            .build())
                    .toList();
        }

        return QuestionResponse.builder()
                .id(question.getId())
                .surveyId(question.getSurvey().getId())
                .questionText(question.getQuestionText())
                .questionType(question.getQuestionType())
                .required(question.getRequired())
                .orderNumber(question.getOrderNumber())
                .options(options)
                .build();
    }

}