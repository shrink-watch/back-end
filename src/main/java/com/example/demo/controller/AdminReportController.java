package com.example.demo.controller;

import com.example.demo.dto.ReportResponse;
import com.example.demo.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 관리자용 제보 검수 API
 * (인증/인가는 추후 적용 — 현재는 경로만 분리)
 */
@RestController
@RequestMapping("/api/admin/reports")
public class AdminReportController {

    private final ReportService reportService;

    public AdminReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 승인 대기 목록 조회 (PENDING)
    @GetMapping("/pending")
    public List<ReportResponse> getPendingReports() {
        return reportService.getPendingReports();
    }

    // 제보 승인
    @PatchMapping("/{reportId}/approve")
    public ReportResponse approve(@PathVariable Long reportId) {
        return reportService.approve(reportId);
    }

    // 제보 반려
    @PatchMapping("/{reportId}/reject")
    public ReportResponse reject(@PathVariable Long reportId) {
        return reportService.reject(reportId);
    }
}
