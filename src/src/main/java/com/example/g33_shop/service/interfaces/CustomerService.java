package com.example.g33_shop.service.interfaces;

import com.example.g33_shop.domain.entity.Customer;

import java.math.BigDecimal;
import java.util.List;

/**
 * • Сохранить покупателя в базе данных (при сохранении покупатель автоматически считается активным).
 * • Вернуть всех покупателей из базы данных (активных).
 * • Вернуть одного покупателя из базы данных по его идентификатору (если он активен).
 * • Изменить одного покупателя в базе данных по его идентификатору.
 * • Удалить покупателя из базы данных по его идентификатору.
 * • Удалить покупателя из базы данных по его имени.
 * • Восстановить удалённого покупателя в базе данных по его идентификатору.
 * • Вернуть общее количество покупателей в базе данных (активных).
 * • Вернуть стоимость корзины покупателя по его идентификатору (если он активен).
 * • Вернуть среднюю стоимость продукта в корзине покупателя по его идентификатору (если он активен)
 * • Добавить товар в корзину покупателя по их идентификаторам (если оба активны)
 * • Удалить товар из корзины покупателя по их идентификаторам
 * • Полностью очистить корзину покупателя по его идентификатору (если он активен)
 */
public interface CustomerService {

    Customer save(Customer customer);
    List<Customer> getAllActiveCustomers();
    Customer getById(Long id);
    Customer update(Customer customer);
    void deleteById(Long id);
    void deleteByName(String name);
    void restoreById(Long id);
    long getActiveCustomersNumber();
    BigDecimal getTotalCostOfCustomersProducts(Long customerId);
    BigDecimal getAverageCostOfCustomersProducts(Long customerId);
    void addProductToCustomersCart(Long customerId, Long productId);
    void removeProductFromCustomersCart(Long customerId, Long productId);
    void clearCustomersCart(Long customerId);
}
