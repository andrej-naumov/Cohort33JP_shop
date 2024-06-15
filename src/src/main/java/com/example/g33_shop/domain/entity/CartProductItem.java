package com.example.g33_shop.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cart_product")
public class CartProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public CartProductItem() {
    }

    public CartProductItem(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductItem that = (CartProductItem) o;
        return quantity == that.quantity &&
                Objects.equals(id, that.id) &&
                Objects.equals(cart, that.cart) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cart, product, quantity);
    }

    @Override
    public String toString() {
        return String.format("CartProductItem: id - %d, cartId - %d, productId - %d, quantity - %d",
                id, cart.getId(), product.getId(), quantity);
    }
}
