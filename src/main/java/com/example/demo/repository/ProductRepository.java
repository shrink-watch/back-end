package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 상품명에 키워드가 포함된 상품 검색
    List<Product> findByNameContaining(String keyword);

    // 바코드 정확히 일치하는 상품 검색
    List<Product> findByBarcode(String barcode);

    // 카테고리별 조회
    List<Product> findByCategoryId(Long id);

        //메인 홈에 쓰일 가격 동결 추천 (+상품평이 좋은 순)
        List<Product> findTop10ByInflationRateOrderByRatingDesc(Double InflationRate);

        //메인 홈에 쓰일 물가 상승률 높은 랭킹순 (슈링크 플레이션 뿐만 아니라 그냥 가격 상승도 포함)
        List<Product> findTop10ByOrderByInflationRateDesc();

        //메인 홈에 쓰일 용량 감소이면서 물가 상승률이 높은 순 (<- 실제 우리 서비스 취지에 맞는 진짜 슈링크 플레이션)
        List<Product> findTop10ByIsCapacityDecreasedTrueOrderByInflationRateDesc();
        //공식 적발 상품?
        @Query("Select p from Product p where p.isDetected=true")
        List<Product> findDetectedproducts();

}
