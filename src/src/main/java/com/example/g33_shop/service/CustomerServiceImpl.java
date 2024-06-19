package com.example.g33_shop.service;

import com.example.g33_shop.domain.dto.CustomerDto;
import com.example.g33_shop.domain.entity.Customer;
import com.example.g33_shop.repository.CustomerRepository;
import com.example.g33_shop.service.interfaces.CustomerService;
import com.example.g33_shop.service.mapping.CustomerMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMappingService mappingService;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional // Требуется для сохранения и возвращения сущности Customer
    public CustomerDto save(CustomerDto customerDto) {
        // Преобразуем CustomerDto в сущность Customer
        Customer customer = mappingService.mapDtoToEntity(customerDto);
        customer.setId(null); // Устанавливаем id в null для нового клиента
        customer.setActive(true); // По умолчанию считаем, что новый клиент активен
        Customer savedCustomer = repository.save(customer);
        // Преобразуем сохраненную сущность обратно в DTO и возвращаем
        return mappingService.mapEntityToDto(savedCustomer);
    }

    @Override
    public List<CustomerDto> getAllActiveCustomers() {
        return repository.findAllActive().stream()
                .map(mappingService::mapEntityToDto) // Преобразуем сущность в DTO
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getById(Long id) {
        return repository.findById(id)
                .filter(Customer::isActive)
                .map(mappingService::mapEntityToDto) // Преобразуем сущность в DTO
                .orElse(null);
    }

    @Override
    @Transactional // Требуется для сохранения обновленной сущности Customer
    public CustomerDto update(CustomerDto customerDto) {
        // Проверяем, что у клиента задан идентификатор
        if (customerDto.getId() == null) {
            throw new IllegalArgumentException("Customer ID must not be null for update");
        }

        // Ищем существующего клиента по ID
        Customer existingCustomer = repository.findById(customerDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Обновляем существующего клиента с помощью данных из DTO
        existingCustomer.setName(customerDto.getName());
        // Другие поля также можно обновить по необходимости

        // Сохраняем обновленного клиента
        Customer updatedCustomer = repository.save(existingCustomer);

        // Преобразуем обновленную сущность обратно в DTO и возвращаем
        return mappingService.mapEntityToDto(updatedCustomer);
    }


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        Customer customer = repository.findByName(name);
        if (customer != null) {
            repository.delete(customer);
        }
    }

    @Override
    @Transactional // Требуется для сохранения обновленной сущности Customer
    public void restoreById(Long id) {
        Customer customer = repository.findById(id).orElse(null);
        if (customer != null) {
            customer.setActive(true);
            repository.save(customer);
        }
    }

    @Override
    public long getActiveCustomersNumber() {
        return repository.countAllActive();
    }

    @Override
    public BigDecimal getTotalCostOfCustomersProducts(Long customerId) {
        return repository.calculateTotalCostOfProducts(customerId);
    }

    @Override
    public BigDecimal getAverageCostOfCustomersProducts(Long customerId) {
        return repository.calculateAverageCostOfProducts(customerId);
    }

    @Override
    public void addProductToCustomersCart(Long customerId, Long productId) {
        // Здесь может быть логика добавления продукта в корзину клиента
        // Например, использование отдельного сервиса или репозитория для корзины
    }

    @Override
    public void removeProductFromCustomersCart(Long customerId, Long productId) {
        // Здесь может быть логика удаления продукта из корзины клиента
        // Например, использование отдельного сервиса или репозитория для корзины
    }

    @Override
    public void clearCustomersCart(Long customerId) {
        // Здесь может быть логика очистки корзины клиента
        // Например, использование отдельного сервиса или репозитория для корзины
    }
}
