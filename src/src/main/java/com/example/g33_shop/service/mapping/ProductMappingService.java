package com.example.g33_shop.service.mapping;

import com.example.g33_shop.domain.dto.ProductDto;
import com.example.g33_shop.domain.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMappingService {


       public Product mapDtoToEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setActive(true);
        return entity;
    }

    public ProductDto mapEntityToDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
