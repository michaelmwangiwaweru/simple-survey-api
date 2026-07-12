package com.survey_backend_sky.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id", nullable = false)
    private Response response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(columnDefinition = "TEXT")
    private String answerText;

    // Original file name shown to users
    @Column(name = "file_name")
    private String fileName;

    // Actual stored filename on the server
    @Column(name = "stored_file_name")
    private String storedFileName;

    // File MIME type
    @Column(name = "content_type")
    private String contentType;

    // File size in bytes
    @Column(name = "file_size")
    private Long fileSize;
}