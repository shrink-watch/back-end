package com.example.demo.dto;

import com.example.demo.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * GET /api/products/search 응답 1건
 */
@Getter
public class ProductSearchResponse {

    private final Long id;
    private final String name;
    private final Integer price;

    @JsonProperty("unit_price_text")
    private final String unitPriceText;

    private final String barcode;

    @JsonProperty("is_detected")
    private final Boolean isDetected;

    @JsonProperty("annual_damage_cost")
    private final Integer annualDamageCost;

    @JsonProperty("inflation_rate")
    private final Double inflationRate;

    public ProductSearchResponse(Product p) {
        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
        this.unitPriceText = p.getUnitPriceText();
        this.barcode = p.getBarcode();
        this.isDetected = p.getIsDetected();
        this.annualDamageCost = p.getAnnualDamageCost();
        this.inflationRate = p.getInflationRate();
    }
}
