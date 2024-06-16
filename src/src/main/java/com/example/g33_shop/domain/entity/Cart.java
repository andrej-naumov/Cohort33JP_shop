package com.example.g33_shop.domain.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CartProductItem> cartProductItems = new ArrayList<>();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public List<CartProductItem> getCartProductItems() {
        return cartProductItems;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCartProductItems(List<CartProductItem> cartProductItems) {
        this.cartProductItems = cartProductItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                '}';
    }
}
