package com.example.demo.dto;

import com.example.demo.entity.Report;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * GET /api/reports 응답 1건
 */
@Getter
public class ReportResponse {

    private final Long reportId;
    private final String nickname;
    private final String productName;
    private final String content;
    private final LocalDateTime createdAt;

    public ReportResponse(Report r) {
        this.reportId = r.getReportId();
        this.nickname = r.getNickname();
        this.productName = r.getProductName();
        this.content = r.getContent();
        this.createdAt = r.getCreatedAt();
    }
}
