package com.example.g33_shop.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProductItem> cartProductItems = new ArrayList<>();

    public Cart() {
    }

    public Cart(Long id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartProductItem> getCartProductItems() {
        return cartProductItems;
    }

    public void setCartProductItems(List<CartProductItem> cartProductItems) {
        this.cartProductItems = cartProductItems;
    }

    public Collection<CartProductItem> getProducts() {
        return cartProductItems;
    }

    public void addProduct(CartProductItem product) {
        cartProductItems.add(product);
        product.setCart(this);
    }

    public void removeProduct(CartProductItem product) {
        cartProductItems.remove(product);
        product.setCart(null);
    }

    public void clearProducts() {
        for (CartProductItem product : cartProductItems) {
            product.setCart(null);
        }
        cartProductItems.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(customer, cart.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer);
    }

    @Override
    public String toString() {
        return String.format("Cart: id - %d, customer - %s", id, customer.getName());
    }
}
