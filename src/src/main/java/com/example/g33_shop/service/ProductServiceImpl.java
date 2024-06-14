package com.example.g33_shop.service;

import com.example.g33_shop.domain.entity.Product;
import com.example.g33_shop.repository.interfaces.ProductRepository;
import com.example.g33_shop.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.findAllActive();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product update(Product product) {
        if (product.getId() == null || !productRepository.findById(product.getId()).isPresent()) {
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteByTitle(String title) {
        productRepository.deleteByTitle(title);
    }

    @Override
    public void restoreById(Long id) {
        productRepository.restoreById(id);
    }

    @Override
    public long getAllActiveProductsQuantity() {
        return productRepository.countAllActive();
    }

    @Override
    public BigDecimal getAllActiveProductsTotalPrice() {
        return productRepository.calculateTotalPriceOfAllActive();
    }

    @Override
    public BigDecimal getAllActiveProductsAveragePrice() {
        return productRepository.calculateAveragePriceOfAllActive();
    }
}
