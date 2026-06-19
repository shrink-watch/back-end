package com.example.demo.dto;

import com.example.demo.entity.ProductPriceHistory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChartDataResponse {
    private String date;
    private Integer normalPrice;
    private Integer unitPrice;

    public ChartDataResponse(ProductPriceHistory history){
        this.date= history.getDate();
        this.normalPrice=history.getNormalPrice();
        this.unitPrice= history.getUnitPrice();
    }
}
