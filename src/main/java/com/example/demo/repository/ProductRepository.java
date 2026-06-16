package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 상품명에 키워드가 포함된 상품 검색
    List<Product> findByNameContaining(String keyword);

    // 바코드 정확히 일치하는 상품 검색
    List<Product> findByBarcode(String barcode);
}
