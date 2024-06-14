package com.example.g33_shop.repository;

import com.example.g33_shop.domain.entity.Product;
import com.example.g33_shop.repository.interfaces.ProductRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryMapImpl implements ProductRepository {

    private Map<Long, Product> productMap = new HashMap<>();
    private long currentId = 1;

    public ProductRepositoryMapImpl() {
        // Добавляем продукты в карту
        save(new Product(null, "Яблоко", new BigDecimal("1.00"), true));
        save(new Product(null, "Банан", new BigDecimal("0.50"), true));
        save(new Product(null, "Киви", new BigDecimal("1.20"), true));
        save(new Product(null, "Апельсин", new BigDecimal("0.80"), true));
        save(new Product(null, "Лимон", new BigDecimal("0.70"), true));
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(currentId++);
        }
        productMap.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public List<Product> findAllActive() {
        return productMap.values().stream()
                .filter(Product::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Product product = productMap.get(id);
        if (product != null) {
            product.setActive(false);
            productMap.put(id, product);
        }
    }

    @Override
    public void deleteByTitle(String title) {
        productMap.values().stream()
                .filter(product -> product.getTitle().equals(title))
                .forEach(product -> product.setActive(false));
    }

    @Override
    public void restoreById(Long id) {
        Product product = productMap.get(id);
        if (product != null) {
            product.setActive(true);
            productMap.put(id, product);
        }
    }

    @Override
    public long countAllActive() {
        return productMap.values().stream()
                .filter(Product::isActive)
                .count();
    }

    @Override
    public BigDecimal calculateTotalPriceOfAllActive() {
        return productMap.values().stream()
                .filter(Product::isActive)
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal calculateAveragePriceOfAllActive() {
        List<BigDecimal> prices = productMap.values().stream()
                .filter(Product::isActive)
                .map(Product::getPrice)
                .collect(Collectors.toList());
        return prices.isEmpty() ? BigDecimal.ZERO : prices.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(prices.size()), BigDecimal.ROUND_HALF_UP);
    }
}
