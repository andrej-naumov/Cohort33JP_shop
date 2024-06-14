package com.example.g33_shop.repository;

import com.example.g33_shop.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Здесь можно добавить дополнительные методы для работы с Customer, если потребуется
}
