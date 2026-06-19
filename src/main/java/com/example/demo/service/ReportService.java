package com.example.demo.service;

import com.example.demo.dto.ReportCreateRequest;
import com.example.demo.dto.ReportResponse;
import com.example.demo.entity.Report;
import com.example.demo.entity.ReportStatus;
import com.example.demo.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // 제보 피드: 승인된(APPROVED) 제보만 최신순 조회
    public List<ReportResponse> getReports() {
        return reportRepository.findByStatusOrderByCreatedAtDesc(ReportStatus.APPROVED).stream()
                .map(ReportResponse::new)
                .toList();
    }

    // 신규 제보 등록 (등록 시 status=PENDING)
    public void create(ReportCreateRequest request) {
        Report report = new Report(
                request.getProductName(),
                request.getContent(),
                request.getPreviousVolume(),
                request.getCurrentVolume(),
                request.getPrice(),
                request.getStore(),
                request.getImageUrl()
        );
        reportRepository.save(report);
    }

    // [관리자] 승인 대기 목록 조회 (PENDING 최신순)
    public List<ReportResponse> getPendingReports() {
        return reportRepository.findByStatusOrderByCreatedAtDesc(ReportStatus.PENDING).stream()
                .map(ReportResponse::new)
                .toList();
    }

    // [관리자] 제보 승인
    @Transactional
    public ReportResponse approve(Long reportId) {
        Report report = findById(reportId);
        report.approve();
        return new ReportResponse(report);
    }

    // [관리자] 제보 반려
    @Transactional
    public ReportResponse reject(Long reportId) {
        Report report = findById(reportId);
        report.reject();
        return new ReportResponse(report);
    }

    private Report findById(Long reportId) {
        return reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("제보를 찾을 수 없습니다. reportId=" + reportId));
    }
}
