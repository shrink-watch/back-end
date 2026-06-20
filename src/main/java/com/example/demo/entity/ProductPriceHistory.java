package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "productPriceHistories")
public class ProductPriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime date;

    private Integer normalPrice;

    private Integer unitPrice;

    private Integer pastCapacity;

}
