package com.example.g33_shop.controller;

import com.example.g33_shop.domain.entity.Customer;
import com.example.g33_shop.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // Create: POST -> localhost:8080/customers
    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    // Read: GET -> localhost:8080/customers?id=3
    @GetMapping
    public List<Customer> get(@RequestParam(required = false) Long id) {
        if (id == null) {
            return service.getAllActiveCustomers();
        } else {
            Customer customer = service.getById(id);
            return customer == null ? null : List.of(customer);
        }
    }

    // Update: PUT -> localhost:8080/customers
    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return service.update(customer);
    }

    // Delete: DELETE -> localhost:8080/customers?id=3
    @DeleteMapping
    public void delete(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        if (id != null) {
            service.deleteById(id);
        } else if (name != null) {
            service.deleteByName(name);
        }
    }

    // Restore: PUT -> localhost:8080/customers/restore?id=3
    @PutMapping("/restore")
    public void restore(@RequestParam Long id) {
        service.restoreById(id);
    }

    // Get the number of active customers: GET -> localhost:8080/customers/quantity
    @GetMapping("/quantity")
    public long getActiveCustomersNumber() {
        return service.getActiveCustomersNumber();
    }

    // Get total cost of customer's products: GET -> localhost:8080/customers/{customerId}/total-cost
    @GetMapping("/{customerId}/total-cost")
    public BigDecimal getTotalCostOfCustomersProducts(@PathVariable Long customerId) {
        return service.getTotalCostOfCustomersProducts(customerId);
    }

    // Get average cost of customer's products: GET -> localhost:8080/customers/{customerId}/average-cost
    @GetMapping("/{customerId}/average-cost")
    public BigDecimal getAverageCostOfCustomersProducts(@PathVariable Long customerId) {
        return service.getAverageCostOfCustomersProducts(customerId);
    }

    // Add product to customer's cart: PUT -> localhost:8080/customers/{customerId}/add-product
    @PutMapping("/{customerId}/add-product")
    public void addProductToCustomersCart(@PathVariable Long customerId, @RequestParam Long productId) {
        service.addProductToCustomersCart(customerId, productId);
    }

    // Remove product from customer's cart: DELETE -> localhost:8080/customers/{customerId}/remove-product
    @DeleteMapping("/{customerId}/remove-product")
    public void removeProductFromCustomersCart(@PathVariable Long customerId, @RequestParam Long productId) {
        service.removeProductFromCustomersCart(customerId, productId);
    }

    // Clear customer's cart: DELETE -> localhost:8080/customers/{customerId}/clear-cart
    @DeleteMapping("/{customerId}/clear-cart")
    public void clearCustomersCart(@PathVariable Long customerId) {
        service.clearCustomersCart(customerId);
    }
}
