package com.example.g33_shop.repository.interfaces;

import com.example.g33_shop.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findAllActive();

    void deleteById(Long id);

    void deleteByTitle(String title);

    void restoreById(Long id);

    long countAllActive();

    BigDecimal calculateTotalPriceOfAllActive();

    BigDecimal calculateAveragePriceOfAllActive();
}

