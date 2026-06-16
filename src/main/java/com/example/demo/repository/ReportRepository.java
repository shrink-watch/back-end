package com.example.demo.repository;

import com.example.demo.entity.report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<report, Long> {
}