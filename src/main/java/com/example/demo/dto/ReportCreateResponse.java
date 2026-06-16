package com.example.demo.dto;

import lombok.Getter;

/**
 * POST /api/reports 응답 (201 Created)
 */
@Getter
public class ReportCreateResponse {

    private final boolean success;
    private final String message;

    public ReportCreateResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
