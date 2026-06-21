package com.example.demo.dto;

import com.example.demo.entity.AlternativeProduct;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductPriceHistory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * GET /api/products/:id 응답
 * chartData, alternativeProducts 는 현재 데이터가 없어 빈 배열로 내려준다. (추후 채움)
 */
@Getter
public class ProductDetailResponse {

    private final Long id;
    private final String name;
    private final Integer price;

    @JsonProperty("unit_price_text")
    private final String unitPriceText;

    @JsonProperty("is_detected")
    private final Boolean isDetected;

    private final Double rating;

    private final List<ChartDataResponse> chartData;
    private final List<AlternativeSearchResponse> alternativeProducts;

    private final String categoryName;

    public ProductDetailResponse(Product p, List<ProductPriceHistory> chartData, List<AlternativeProduct> alternativeProducts) {
        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
        this.unitPriceText = p.getUnitPriceText();
        this.isDetected = p.getIsDetected();
        this.rating = p.getRating();
        this.chartData = chartData.stream()
                .map(ChartDataResponse::new)
                .toList();
        this.alternativeProducts = alternativeProducts.stream()
                .map(AlternativeSearchResponse::new)
                .toList();
        this.categoryName = p.getCategory() != null ? p.getCategory().getName() : null;
    }
}
