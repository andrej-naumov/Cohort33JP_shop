package com.example.g33_shop.service;

import com.example.g33_shop.domain.entity.Customer;
import com.example.g33_shop.repository.CustomerRepository;
import com.example.g33_shop.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        customer.setId(null);
        customer.setActive(true); // По умолчанию считаем, что новый клиент активен
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return repository.findAllActive();
    }

    @Override
    public Customer getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        // Проверяем, что у клиента задан идентификатор
        if (customer.getId() == null) {
            throw new IllegalArgumentException("Customer ID must not be null for update");
        }
        return repository.save(customer);
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
