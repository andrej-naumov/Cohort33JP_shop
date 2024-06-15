package com.example.g33_shop.controller;

import com.example.g33_shop.domain.entity.CartProductItem;
import com.example.g33_shop.domain.entity.Product;
import com.example.g33_shop.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/add")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long cartId, @RequestBody CartProductItem productItem) {
        cartService.addProductToCart(cartId, productItem);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}/products")
    public ResponseEntity<List<CartProductItem>> getAllProductsInCart(@PathVariable Long cartId) {
        List<CartProductItem> products = cartService.getAllActiveProductsInCart(cartId);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}/totalPrice")
    public ResponseEntity<BigDecimal> getTotalPrice(@PathVariable Long cartId) {
        BigDecimal totalPrice = cartService.getTotalPriceOfActiveProductsInCart(cartId);
        return ResponseEntity.ok(totalPrice);
    }

    @GetMapping("/{cartId}/averagePrice")
    public ResponseEntity<BigDecimal> getAveragePrice(@PathVariable Long cartId) {
        BigDecimal averagePrice = cartService.getAveragePriceOfActiveProductsInCart(cartId);
        return ResponseEntity.ok(averagePrice);
    }
}
