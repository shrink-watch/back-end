package com.example.demo.repository;

import com.example.demo.entity.Report;
import com.example.demo.entity.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    // 제보 피드: 특정 상태(주로 APPROVED)만 최신순
    List<Report> findByStatusOrderByCreatedAtDesc(ReportStatus status);
}
