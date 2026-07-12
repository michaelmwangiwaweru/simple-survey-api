package com.survey_backend_sky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    // Admin Dashboard
    private long totalUsers;
    private long totalSurveys;
    private long totalResponses;
    private long activeSurveys;

    // User Dashboard
    private long availableSurveys;
    private long completedSurveys;
    private long pendingSurveys;

}