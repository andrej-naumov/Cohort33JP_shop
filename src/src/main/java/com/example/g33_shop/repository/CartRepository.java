package com.example.g33_shop.repository;

import com.example.g33_shop.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
