package com.example.g33_shop.service;

import com.example.g33_shop.domain.entity.Cart;
import com.example.g33_shop.domain.entity.CartProductItem;
import com.example.g33_shop.domain.entity.Product;
import com.example.g33_shop.repository.CartProductRepository;
import com.example.g33_shop.repository.CartRepository;
import com.example.g33_shop.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void addProductToCart(Long cartId, CartProductItem productItem) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent() && productItem.getProduct().isActive()) {
            Cart cart = cartOptional.get();
            cart.getProducts().add(productItem);
            productItem.setCart(cart);
            cartProductRepository.save(productItem);
            cartRepository.save(cart);
        }
    }

    @Override
    public List<CartProductItem> getAllActiveProductsInCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            return cartOptional.get().getProducts().stream()
                    .filter(cartProductItem -> cartProductItem.getProduct().isActive())
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    public void removeProductFromCart(Long cartId, Long productId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getProducts().removeIf(cartProductItem -> cartProductItem.getProduct().getId().equals(productId));
            cartRepository.save(cart);
        }
    }

    @Override
    public void clearCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getProducts().clear();
            cartRepository.save(cart);
        }
    }

    @Override
    public BigDecimal getTotalPriceOfActiveProductsInCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            return cartOptional.get().getProducts().stream()
                    .filter(cartProductItem -> cartProductItem.getProduct().isActive())
                    .map(cartProductItem -> cartProductItem.getProduct().getPrice())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getAveragePriceOfActiveProductsInCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            List<BigDecimal> prices = cartOptional.get().getProducts().stream()
                    .filter(cartProductItem -> cartProductItem.getProduct().isActive())
                    .map(cartProductItem -> cartProductItem.getProduct().getPrice())
                    .collect(Collectors.toList());
            return prices.stream()
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(new BigDecimal(prices.size()), BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}
