package com.example.demo.controller;

import com.example.demo.dto.ProductDetailResponse;
import com.example.demo.dto.ProductSearchResponse;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. 상품 통합 검색 (이름 또는 바코드)
    @GetMapping("/search")
    public List<ProductSearchResponse> search(@RequestParam String keyword) {
        return productService.search(keyword);
    }

    // 2. 상품 상세 정보 조회
    @GetMapping("/{id}")
    public ProductDetailResponse getDetail(@PathVariable Long id) {
        return productService.getDetail(id);
    }
}
