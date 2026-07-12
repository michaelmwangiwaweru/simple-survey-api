package com.survey_backend_sky.service;

import com.survey_backend_sky.dto.*;
import com.survey_backend_sky.entity.*;
import com.survey_backend_sky.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final AnswerRepository answerRepository;
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public ResponseResponse submitSurvey(
            Long surveyId,
            SubmitSurveyRequest request,
            List<MultipartFile> files,
            Authentication authentication
    ) throws IOException {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        Response response = Response.builder()
                .survey(survey)
                .user(user)
                .build();

        response = responseRepository.save(response);

        Path uploadDir = Paths.get("uploads");

        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        int fileIndex = 0;

        for (AnswerRequest answerRequest : request.getAnswers()) {

            Question question = questionRepository.findById(answerRequest.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            String answerValue = answerRequest.getAnswer();

            if (question.getQuestionType() == QuestionType.FILE_UPLOAD) {

                if (files != null && fileIndex < files.size()) {

                    MultipartFile file = files.get(fileIndex++);

                    if (!file.isEmpty()) {

                        String fileName =
                                System.currentTimeMillis() + "_" +
                                        file.getOriginalFilename();

                        Path destination = uploadDir.resolve(fileName);

                        Files.copy(
                                file.getInputStream(),
                                destination,
                                StandardCopyOption.REPLACE_EXISTING
                        );

                        answerValue = fileName;
                    }
                }
            }

            Answer answer = Answer.builder()
                    .response(response)
                    .question(question)
                    .answerText(answerValue)
                    .build();

            answerRepository.save(answer);
        }

        return ResponseResponse.builder()
                .id(response.getId())
                .surveyId(survey.getId())
                .surveyTitle(survey.getTitle())
                .submittedBy(user.getFirstName() + " " + user.getLastName())
                .submittedByEmail(user.getEmail())
                .submittedAt(response.getSubmittedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ResponseResponse> getAllResponses() {

        return responseRepository.findAllByOrderBySubmittedAtDesc()
                .stream()
                .map(response -> ResponseResponse.builder()
                        .id(response.getId())
                        .surveyId(response.getSurvey().getId())
                        .surveyTitle(response.getSurvey().getTitle())
                        .submittedBy(
                                response.getUser().getFirstName() + " " +
                                        response.getUser().getLastName()
                        )
                        .submittedByEmail(response.getUser().getEmail())
                        .submittedAt(response.getSubmittedAt())
                        .build())
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ResponseResponse> getSurveyResponses(Long surveyId) {

        return responseRepository.findBySurveyIdOrderBySubmittedAtDesc(surveyId)
                .stream()
                .map(response -> ResponseResponse.builder()
                        .id(response.getId())
                        .surveyId(response.getSurvey().getId())
                        .surveyTitle(response.getSurvey().getTitle())
                        .submittedBy(
                                response.getUser().getFirstName() + " " +
                                        response.getUser().getLastName()
                        )
                        .submittedByEmail(response.getUser().getEmail())
                        .submittedAt(response.getSubmittedAt())
                        .build())
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ResponseResponse> getMyResponses(Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return responseRepository.findByUserId(user.getId())
                .stream()
                .map(response -> ResponseResponse.builder()
                        .id(response.getId())
                        .surveyId(response.getSurvey().getId())
                        .surveyTitle(response.getSurvey().getTitle())
                        .submittedBy(
                                user.getFirstName() + " " +
                                        user.getLastName()
                        )
                        .submittedByEmail(user.getEmail())
                        .submittedAt(response.getSubmittedAt())
                        .build())
                .toList();
    }

    @Transactional(readOnly = true)
    public ResponseDetailsResponse getResponse(Long responseId) {

        Response response = responseRepository.findById(responseId)
                .orElseThrow(() ->
                        new RuntimeException("Response not found"));

        List<AnswerDto> answers = answerRepository.findByResponseId(responseId)
                .stream()
                .map(answer -> {

                    boolean isFile =
                            answer.getQuestion().getQuestionType()
                                    == QuestionType.FILE_UPLOAD;

                    String originalFileName = null;

                    if (isFile && answer.getAnswerText() != null) {

                        int index = answer.getAnswerText().indexOf("_");

                        if (index > -1) {
                            originalFileName =
                                    answer.getAnswerText().substring(index + 1);
                        } else {
                            originalFileName = answer.getAnswerText();
                        }
                    }

                    return AnswerDto.builder()
                            .questionId(answer.getQuestion().getId())
                            .question(answer.getQuestion().getQuestionText())
                            .answer(answer.getAnswerText())
                            .file(isFile)
                            .fileName(originalFileName)
                            .build();

                })
                .toList();

        return ResponseDetailsResponse.builder()
                .id(response.getId())
                .surveyId(response.getSurvey().getId())
                .surveyTitle(response.getSurvey().getTitle())
                .submittedBy(
                        response.getUser().getFirstName() + " " +
                                response.getUser().getLastName()
                )
                .submittedAt(response.getSubmittedAt())
                .answers(answers)
                .build();
    }

}