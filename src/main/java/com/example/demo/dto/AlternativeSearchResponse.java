package com.example.demo.dto;

import com.example.demo.entity.AlternativeProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AlternativeSearchResponse {
    private String name;
    private Integer price;
    @JsonProperty("unit_price_text")
    private String unitPriceText;
    private Double rating;
    @JsonProperty("coupang_url")
    private String URL;

    public AlternativeSearchResponse(AlternativeProduct alternativeProduct){
        this.name=alternativeProduct.getName();
        this.price=alternativeProduct.getPrice();
        this.unitPriceText= alternativeProduct.getUnitPriceText();
        this.rating= alternativeProduct.getRating();
        this.URL= alternativeProduct.getURL();;
    }
}
