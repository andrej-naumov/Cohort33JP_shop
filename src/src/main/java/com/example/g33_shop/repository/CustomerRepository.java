package com.example.g33_shop.repository;

import com.example.g33_shop.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.active = true")
    List<Customer> findAllActive();

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.active = true")
    long countAllActive();

    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    Customer findByName(String name);

    @Query("SELECT 1 FROM Product p WHERE 1=1") //TODO -
    BigDecimal calculateTotalCostOfProducts(Long customerId);

    @Query("SELECT 1 FROM Product p WHERE 1=1") //TODO -
    BigDecimal calculateAverageCostOfProducts(@Param("customerId") Long customerId);
}
