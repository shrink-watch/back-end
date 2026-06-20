package com.example.demo.dto;

import lombok.Getter;

/**
 * 공통 에러 응답 포맷
 */
@Getter
public class ErrorResponse {

    private final int status;
    private final String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
