package com.example.demo.service;

import com.example.demo.dto.ReportCreateRequest;
import com.example.demo.dto.ReportResponse;
import com.example.demo.entity.Report;
import com.example.demo.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // 제보 피드 최신순 조회
    public List<ReportResponse> getReports() {
        return reportRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ReportResponse::new)
                .toList();
    }

    // 신규 제보 등록
    public void create(ReportCreateRequest request) {
        Report report = new Report(request.getProductName(), request.getContent());
        reportRepository.save(report);
    }
}
