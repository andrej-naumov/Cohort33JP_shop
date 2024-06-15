package com.example.g33_shop.service;


import com.example.g33_shop.domain.entity.Product;
import com.example.g33_shop.repository.ProductRepository;
import com.example.g33_shop.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        product.setId(null);
        product.setActive(true);
        return repository.save(product);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return repository.findAll()
                .stream()
                .filter(Product::isActive)
                .toList();
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        return optionalProduct.filter(Product::isActive).orElse(null);
    }

    @Override
    public Product update(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product ID must not be null for update");
        }
        return repository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByTitle(String title) {
        Product product = repository.findByTitle(title);
        if (product != null) {
            repository.delete(product);
        }
    }

    @Override
    public void restoreById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(true);
            repository.save(product);
        }
    }

    @Override
    public long getAllActiveProductsQuantity() {
        return repository.countAllByActiveTrue();
    }

    @Override
    public BigDecimal getAllActiveProductsTotalPrice() {
        return repository.calculateTotalPriceOfAllActiveProducts();
    }

    @Override
    public BigDecimal getAllActiveProductsAveragePrice() {
        return repository.calculateAveragePriceOfAllActiveProducts();
    }
}
