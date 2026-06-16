package com.example.demo.controller;

import com.example.demo.dto.ReportCreateRequest;
import com.example.demo.dto.ReportCreateResponse;
import com.example.demo.dto.ReportResponse;
import com.example.demo.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 3. 제보 피드 전체 목록 조회 (최신순)
    @GetMapping
    public List<ReportResponse> getReports() {
        return reportService.getReports();
    }

    // 4. 신규 소비자 제보 등록
    @PostMapping
    public ResponseEntity<ReportCreateResponse> create(@RequestBody ReportCreateRequest request) {
        reportService.create(request);
        ReportCreateResponse body = new ReportCreateResponse(
                true,
                "소비자 제보 고발이 실시간 피드에 정상 반영되었습니다."
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
