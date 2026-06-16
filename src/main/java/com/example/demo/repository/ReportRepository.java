package com.example.demo.repository;

import com.example.demo.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    // 제보 피드: 최신순 정렬
    List<Report> findAllByOrderByCreatedAtDesc();
}
