package com.homehub.core.login.exception;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class ApiErrorResponse {

    private final String error;
    private final String message;
    private final OffsetDateTime timestamp;
    private final String path;

    public ApiErrorResponse(String error, String message, OffsetDateTime timestamp, String path) {
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }
}
