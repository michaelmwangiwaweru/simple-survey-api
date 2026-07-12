package com.survey_backend_sky.service;

import com.survey_backend_sky.dto.DashboardResponse;
import com.survey_backend_sky.dto.SurveyRequest;
import com.survey_backend_sky.dto.SurveyResponse;
import com.survey_backend_sky.entity.Role;
import com.survey_backend_sky.entity.Survey;
import com.survey_backend_sky.entity.User;
import com.survey_backend_sky.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;
    private final ResponseRepository responseRepository;


    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionOptionRepository optionRepository;


    // CREATE SURVEY
    public SurveyResponse createSurvey(
            SurveyRequest request,
            Authentication authentication
    ) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));


        Survey survey = Survey.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdBy(user)
                .build();


        survey = surveyRepository.save(survey);

        return mapToResponse(survey);
    }



    // GET ALL SURVEYS
    @Transactional(readOnly = true)
    public List<SurveyResponse> getAllSurveys() {

        return surveyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }



    // GET SINGLE SURVEY BY ID
    @Transactional(readOnly = true)
    public SurveyResponse getSurvey(Long id) {

        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));


        return mapToResponse(survey);
    }


    @Transactional(readOnly = true)
    public DashboardResponse getDashboard(Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        long totalSurveys = surveyRepository.count();

        long completed = responseRepository.countByUser(user);

        long pending = totalSurveys - completed;

        long available = pending;

        return DashboardResponse.builder()
                .availableSurveys(available)
                .completedSurveys(completed)
                .pendingSurveys(pending)
                .build();
    }



    // UPDATE SURVEY
    public SurveyResponse updateSurvey(
            Long id,
            SurveyRequest request,
            Authentication authentication
    ) {

        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));


        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));



        // Only owner or ADMIN can edit
        if (!survey.getCreatedBy().getId().equals(currentUser.getId())
                && currentUser.getRole() != Role.ROLE_ADMIN) {

            throw new RuntimeException(
                    "You are not allowed to edit this survey"
            );
        }



        survey.setTitle(request.getTitle());
        survey.setDescription(request.getDescription());


        survey = surveyRepository.save(survey);


        return mapToResponse(survey);
    }




    // DELETE SURVEY




    // DELETE SURVEY
    public void deleteSurvey(
            Long id,
            Authentication authentication
    ) {

        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Only owner or ADMIN can delete
        if (!survey.getCreatedBy().getId().equals(currentUser.getId())
                && currentUser.getRole() != Role.ROLE_ADMIN) {

            throw new RuntimeException(
                    "You are not allowed to delete this survey"
            );
        }

        // ==========================
        // Delete Answers
        // ==========================
        var responses = responseRepository.findBySurveyIdOrderBySubmittedAtDesc(id);

        for (var response : responses) {

            var answers = answerRepository.findByResponseId(response.getId());

            answerRepository.deleteAll(answers);
        }

        // ==========================
        // Delete Responses
        // ==========================
        responseRepository.deleteBySurveyId(id);

        // ==========================
        // Delete Question Options
        // ==========================
        var questions = questionRepository.findBySurveyIdOrderByOrderNumberAsc(id);

        for (var question : questions) {

            optionRepository.deleteByQuestionId(question.getId());
        }

        // ==========================
        // Delete Questions
        // ==========================
        questionRepository.deleteBySurveyId(id);

        // ==========================
        // Finally delete Survey
        // ==========================
        surveyRepository.delete(survey);
    }











    // ENTITY TO DTO MAPPER
    private SurveyResponse mapToResponse(Survey survey) {

        return SurveyResponse.builder()
                .id(survey.getId())
                .title(survey.getTitle())
                .description(survey.getDescription())
                .createdBy(
                        survey.getCreatedBy().getFirstName()
                                + " "
                                + survey.getCreatedBy().getLastName()
                )
                .createdAt(survey.getCreatedAt())
                .build();
    }

}