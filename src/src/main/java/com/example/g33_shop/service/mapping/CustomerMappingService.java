package com.example.g33_shop.service.mapping;

import com.example.g33_shop.domain.dto.CustomerDto;
import com.example.g33_shop.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.g33_shop.domain.dto.CustomerDto;
import com.example.g33_shop.domain.entity.Customer;
import org.springframework.stereotype.Service;

/**  as service is commented see after     */
//@Service
//public class CustomerMappingService {

//    public Customer mapDtoToEntity(CustomerDto dto) {
//        Customer entity = new Customer();
//        entity.setId(dto.getId());
//        entity.setName(dto.getName());
//        entity.setActive(true); // Устанавливаем активность по умолчанию
//        return entity;
//    }
//
//    public CustomerDto mapEntityToDto(Customer entity) {
//        CustomerDto dto = new CustomerDto();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//        return dto;
//    }
//}




@Mapper(componentModel = "spring")
public interface CustomerMappingService {

    // CustomerMappingService INSTANCE = Mappers.getMapper(CustomerMappingService.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Customer mapDtoToEntity(CustomerDto dto);

    CustomerDto mapEntityToDto(Customer entity);
}


