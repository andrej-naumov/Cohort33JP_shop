package com.example.g33_shop.domain.entity;

import java.util.Date;
import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Date registeredDate;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    // Override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(registeredDate, customer.registeredDate);
    }

    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, registeredDate);
    }

    // Override toString method
    @Override
    public String toString() {
        return String.format("Customer: id - %d, name - %s, email - %s, phone - %s, registeredDate - %s",
                id, name, email, phone, registeredDate);
    }
}
