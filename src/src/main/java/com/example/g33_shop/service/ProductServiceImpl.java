package com.example.g33_shop.service;


import com.example.g33_shop.domain.dto.ProductDto;
import com.example.g33_shop.domain.entity.Product;
import com.example.g33_shop.repository.ProductRepository;
import com.example.g33_shop.service.interfaces.ProductService;
import com.example.g33_shop.service.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMappingService mappingService;

    public ProductServiceImpl(ProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = mappingService.mapDtoToEntity(productDto);
        product.setId(null);
        product.setActive(true);
        return mappingService.mapEntityToDto(repository.save(product));
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {
        return repository.findAll()
                .stream()
                .filter(Product::isActive) // Фильтруем только активные продукты
                .map(mappingService::mapEntityToDto) // Преобразуем каждый продукт в ProductDto
                .collect(Collectors.toList()); // Собираем результаты обратно в список
    }

    @Override
    public ProductDto getById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        return optionalProduct
                .filter(Product::isActive) // Фильтруем по активности продукта
                .map(mappingService::mapEntityToDto) // Преобразуем в DTO, если продукт активен
                .orElse(null); // Если продукт не найден или не активен, возвращаем null
    }

    @Override
    public ProductDto update(ProductDto product) {
        // Проверяем, что ID продукта не равен null
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product ID must not be null for update");
        }

        // Поиск продукта в базе данных по ID
        Product existingProduct = repository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + product.getId()));

        // Обновляем информацию о продукте на основе переданного ProductDto
        existingProduct.setTitle(product.getTitle());
        existingProduct.setPrice(product.getPrice());

        // Сохраняем обновленную версию продукта в базе данных и преобразуем её в ProductDto
        Product updatedProduct = repository.save(existingProduct);
        return mappingService.mapEntityToDto(updatedProduct);
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
