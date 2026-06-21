package com.example.demo.dto;

import com.example.demo.entity.ProductPriceHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class ChartDataResponse {
    private String date;
    private Integer normalPrice;
    private Integer unitPrice;

    // 차트 X축 표기용 포맷 (예: "24.01")
    private static final DateTimeFormatter CHART_DATE_FORMAT = DateTimeFormatter.ofPattern("yy.MM");

    public ChartDataResponse(ProductPriceHistory history){
        this.date = history.getDate() != null ? history.getDate().format(CHART_DATE_FORMAT) : null;
        this.normalPrice = history.getNormalPrice();
        this.unitPrice = history.getUnitPrice();
    }
}
