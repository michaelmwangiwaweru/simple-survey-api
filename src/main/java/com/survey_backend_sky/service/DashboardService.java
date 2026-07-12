package com.survey_backend_sky.service;

import com.survey_backend_sky.dto.DashboardResponse;
import com.survey_backend_sky.repository.ResponseRepository;
import com.survey_backend_sky.repository.SurveyRepository;
import com.survey_backend_sky.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardService {

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;
    private final ResponseRepository responseRepository;

    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()
                .totalUsers(userRepository.count())
                .totalSurveys(surveyRepository.count())
                .totalResponses(responseRepository.count())
                .activeSurveys(surveyRepository.count())
                .build();
    }

}