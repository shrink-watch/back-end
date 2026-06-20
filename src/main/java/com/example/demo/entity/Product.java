package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String barcode;

    private Integer price;

    private String unitPriceText;

    private Boolean isDetected;

    private Double rating;

    private Integer annualDamageCost;

    private Double inflationRate;

    private Boolean isCapacityDecreased;

    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "id")
    private Category category;
}
