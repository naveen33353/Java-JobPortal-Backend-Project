package com.aitrich.JobPortalSystem.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    // Constructor for validation errors
    public ErrorResponseDTO(LocalDateTime timestamp, int status, String error, String message, String path, List<ValidationError> validationErrors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ValidationError {
        private String field;
        private String message;
    }
}