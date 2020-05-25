package dev.internetshop.dao;

import dev.internetshop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart> {
    ShoppingCart getByUserId(Long userId);
}
