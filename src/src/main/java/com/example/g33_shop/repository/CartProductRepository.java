package com.example.g33_shop.repository;

import com.example.g33_shop.domain.entity.CartProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductItem, Long> {
    // Дополнительные методы, если требуется
}
