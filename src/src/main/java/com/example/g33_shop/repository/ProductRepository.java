package com.example.g33_shop.repository;


import com.example.g33_shop.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);

    long countAllByActiveTrue();

    @Query("SELECT SUM(p.price) FROM Product p WHERE p.active = true")
    BigDecimal calculateTotalPriceOfAllActiveProducts();

    @Query("SELECT AVG(p.price) FROM Product p WHERE p.active = true")
    BigDecimal calculateAveragePriceOfAllActiveProducts();
}