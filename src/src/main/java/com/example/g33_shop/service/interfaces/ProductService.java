package com.example.g33_shop.service.interfaces;


import com.example.g33_shop.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * • Сохранить продукт в базе данных (при сохранении продукт автоматически считается активным).
 * • Вернуть все продукты из базы данных (активные).
 * • Вернуть один продукт из базы данных по его идентификатору (если он активен).
 * • Изменить один продукт в базе данных по его идентификатору.
 * • Удалить продукт из базы данных по его идентификатору.
 * • Удалить продукт из базы данных по его наименованию.
 * • Восстановить удалённый продукт в базе данных по его идентификатору.
 * • Вернуть общее количество продуктов в базе данных (активных).
 * • Вернуть суммарную стоимость всех продуктов в базе данных (активных).
 * • Вернуть среднюю стоимость продукта в базе данных (из активных).
 * */

public interface ProductService {

    Product save(Product product);
    List<Product> getAllActiveProducts();
    Product getById(Long id);
    Product update(Product product);
    void deleteById(Long id);
    void deleteByTitle(String title);
    void restoreById(Long id);
    long getAllActiveProductsQuantity();
    BigDecimal getAllActiveProductsTotalPrice();
    BigDecimal getAllActiveProductsAveragePrice();
}
