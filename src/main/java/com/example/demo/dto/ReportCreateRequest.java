package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POST /api/reports 요청 바디
 */
@Getter
@Setter
@NoArgsConstructor
public class ReportCreateRequest {

    private String productName;
    private String content;
}
