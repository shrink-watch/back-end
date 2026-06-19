package com.example.demo.entity;

/**
 * 제보 검수 상태
 * PENDING  : 등록 직후, 관리자 승인 대기
 * APPROVED : 관리자 승인 완료, 메인 피드 노출
 * REJECTED : 관리자 반려
 */
public enum ReportStatus {
    PENDING,
    APPROVED,
    REJECTED
}
