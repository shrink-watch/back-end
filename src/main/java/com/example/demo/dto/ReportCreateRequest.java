package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POST /api/reports 요청 바디 (풀스펙)
 */
@Getter
@Setter
@NoArgsConstructor
public class ReportCreateRequest {

    private String productName;
    private String content;

    // 변경 전/후 용량 (g 또는 ml)
    private Double previousVolume;
    private Double currentVolume;

    // 현재 구매 가격 (원)
    private Integer price;

    // 구매처
    private String store;

    // 증빙 이미지 URL
    private String imageUrl;
}
