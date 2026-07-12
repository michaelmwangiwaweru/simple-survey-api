package com.survey_backend_sky.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:5173")
public class FileController {

    private final Path uploadPath = Paths.get("uploads");

    @GetMapping("/{filename:.+}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String filename
    ) throws MalformedURLException {

        Path file = uploadPath.resolve(filename);

        Resource resource = new UrlResource(file.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("File not found.");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\""
                )
                .body(resource);
    }
}