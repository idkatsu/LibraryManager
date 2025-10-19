package com.example.demo.handler;

import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;

    private int status;

    private String message;

    private String path;

    private List<String> errors;

    public ErrorResponse(String message, int status, String path) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status;
        this.path = path;
    }

    public ErrorResponse(String message, int status, String path, List<String> errors) {
        this(message, status, path);
        this.errors = errors;
    }
}
