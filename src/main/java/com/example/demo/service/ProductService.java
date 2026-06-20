package com.example.demo.service;

import com.example.demo.dto.ProductDetailResponse;
import com.example.demo.dto.ProductSearchResponse;
import com.example.demo.entity.AlternativeProduct;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductPriceHistory;
import com.example.demo.repository.AlternativeProductRespository;
import com.example.demo.repository.ProductPriceHistoryRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductPriceHistoryRepository productPriceHistoryRepository;
    private final AlternativeProductRespository alternativeProductRespository;

    // 13자리 숫자면 바코드로, 아니면 상품명으로 검색
    public List<ProductSearchResponse> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }
        String trimmed = keyword.trim();

        List<Product> products = isBarcode(trimmed)
                ? productRepository.findByBarcode(trimmed)
                : productRepository.findByNameContaining(trimmed);

        return products.stream()
                .map(ProductSearchResponse::new)
                .toList();
    }

    public ProductDetailResponse getDetail(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id));
        List<ProductPriceHistory> chartDataList=productPriceHistoryRepository.findAllByProductId(id);
        List<AlternativeProduct> alternativeProductList=alternativeProductRespository.findAllByProductId(id);
        return new ProductDetailResponse(product,chartDataList,alternativeProductList);
    }

    public List<ProductSearchResponse> findProductByCategory(Long id){
        List<Product> products=productRepository.findByCategoryId(id);
        return products.stream()
                .map(ProductSearchResponse::new)
                .toList();
    }

    public List<ProductSearchResponse> findByInflationDesc(){
        return productRepository.findTop10ByOrderByInflationRateDesc().stream()
                .map(ProductSearchResponse::new)
                .toList();
    }

    public List<ProductSearchResponse> findByInflation(){
        return productRepository.findTop10ByInflationRateOrderByRatingDesc(0.0).stream()
                .map(ProductSearchResponse::new)
                .toList();
    }

    public List<ProductSearchResponse> findshrinkflation(){
        return productRepository.findTop10ByIsCapacityDecreasedTrueOrderByInflationRateDesc().stream()
                .map(ProductSearchResponse::new)
                .toList();
    }

    public List<ProductSearchResponse> findDetectedProduct(){
        return productRepository.findDetectedproducts().stream()
                .map(ProductSearchResponse::new)
                .toList();
    }

    private boolean isBarcode(String keyword) {
        return keyword.matches("\\d{13}");
    }
}
