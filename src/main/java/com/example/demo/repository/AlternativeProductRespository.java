package com.example.demo.repository;

import com.example.demo.entity.AlternativeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlternativeProductRespository extends JpaRepository<AlternativeProduct,Long> {
    public List<AlternativeProduct> findAllByProductId(Long id);
}
