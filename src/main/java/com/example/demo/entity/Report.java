package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String nickname;

    private String productName;

    @Column(columnDefinition = "TEXT")
    private String content;

    // 변경 전/후 용량 (g 또는 ml)
    private Double previousVolume;

    private Double currentVolume;

    // 현재 구매 가격 (원)
    private Integer price;

    // 구매처
    private String store;

    // 증빙 이미지 URL (실제 업로드 연동은 추후, 지금은 URL 보관만)
    private String imageUrl;

    // 검수 상태 (등록 시 PENDING)
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    private LocalDateTime createdAt;

    public Report(String productName, String content,
                  Double previousVolume, Double currentVolume,
                  Integer price, String store, String imageUrl) {
        this.productName = productName;
        this.content = content;
        this.previousVolume = previousVolume;
        this.currentVolume = currentVolume;
        this.price = price;
        this.store = store;
        this.imageUrl = imageUrl;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.nickname = "User";
        this.status = ReportStatus.PENDING;
    }

    // 관리자 승인
    public void approve() {
        this.status = ReportStatus.APPROVED;
    }

    // 관리자 반려
    public void reject() {
        this.status = ReportStatus.REJECTED;
    }
}
