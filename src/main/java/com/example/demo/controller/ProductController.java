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

    // 3. 카테고리별 검색
    @GetMapping("/search/category/{id}")
    public List<ProductSearchResponse> getProductByCategory(@PathVariable Long id){
        return productService.findProductByCategory(id);
    }

    // 4. 실질 단가 상승률 TOP 10
    @GetMapping("/search/ranking/inflation")
    public List<ProductSearchResponse> getInflationProduct(){
        return productService.findByInflationDesc();
    }

    // 5. 용량감소 슈링크플레이션 상품 TOP10
    @GetMapping("/search/ranking/capacity")
    public List<ProductSearchResponse> getShrinkflation(){
        return productService.findshrinkflation();
    }

    // 6. 가격동결 상승률 0%인것 TOP10
    @GetMapping("/search/ranking/solid")
    public List<ProductSearchResponse> getNoInflation(){
        return productService.findByInflation();
    }

    // 7. 소비자원 적발(검증완료) 상품
    @GetMapping("/search/ranking/detected")
    public List<ProductSearchResponse> getDetectedProduct(){
        return productService.findDetectedProduct();
    }
}
