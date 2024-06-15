package com.example.g33_shop.service.interfaces;

import com.example.g33_shop.domain.entity.Cart;
import com.example.g33_shop.domain.entity.CartProductItem;
import com.example.g33_shop.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * КОРЗИНА.
 * Корзина имеет следующие свойства:
 * • Целочисленный уникальный идентификатор
 * • Список товаров, лежащий в этой корзине
 * ФУНКЦИОНАЛ КОРЗИНЫ.
 * • Добавить продукт в корзину (если активный)
 * • Получение всех продуктов, находящихся в корзине (активных)
 * • Удалить продукт из корзины по его идентификатору
 * • Полная очистка корзины (удаление всех продуктов)
 * • Получение общей стоимости корзины (активных продуктов)
 * • Получение средней стоимости товара в корзине (из активных продуктов
 */

public interface CartService {
    // Добавить продукт в корзину (если активный)
    void addProductToCart(Long cartId, CartProductItem productItem);

    // Получение всех продуктов, находящихся в корзине (активных)
    List<CartProductItem> getAllActiveProductsInCart(Long cartId);

    // Удалить продукт из корзины по его идентификатору
    void removeProductFromCart(Long cartId, Long productId);

    // Полная очистка корзины (удаление всех продуктов)
    void clearCart(Long cartId);

    // Получение общей стоимости корзины (активных продуктов)
    BigDecimal getTotalPriceOfActiveProductsInCart(Long cartId);

    // Получение средней стоимости товара в корзине (из активных продуктов)
    BigDecimal getAveragePriceOfActiveProductsInCart(Long cartId);

    // Дополнительные методы для управления корзинами
    List<Cart> getAllCarts();
    Optional<Cart> getCartById(Long id);
    Cart saveCart(Cart cart);
    void deleteCart(Long id);
}
