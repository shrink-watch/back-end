package com.example.demo.dto;

import com.example.demo.entity.Report;
import com.example.demo.entity.ReportStatus;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * GET /api/reports 응답 1건 (풀스펙)
 */
@Getter
public class ReportResponse {

    private final Long reportId;
    private final String nickname;
    private final String productName;
    private final String content;
    private final Double previousVolume;
    private final Double currentVolume;
    private final Integer price;
    private final String store;
    private final String imageUrl;
    private final ReportStatus status;
    private final LocalDateTime createdAt;

    public ReportResponse(Report r) {
        this.reportId = r.getReportId();
        this.nickname = r.getNickname();
        this.productName = r.getProductName();
        this.content = r.getContent();
        this.previousVolume = r.getPreviousVolume();
        this.currentVolume = r.getCurrentVolume();
        this.price = r.getPrice();
        this.store = r.getStore();
        this.imageUrl = r.getImageUrl();
        this.status = r.getStatus();
        this.createdAt = r.getCreatedAt();
    }
}
