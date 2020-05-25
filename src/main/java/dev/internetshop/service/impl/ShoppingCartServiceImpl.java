package dev.internetshop.service.impl;

import dev.internetshop.dao.ShoppingCartDao;
import dev.internetshop.lib.Inject;
import dev.internetshop.lib.Service;
import dev.internetshop.model.Product;
import dev.internetshop.model.ShoppingCart;
import dev.internetshop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        boolean remove = shoppingCart.getProducts().remove(product);
        shoppingCartDao.update(shoppingCart);
        return remove;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().removeAll(shoppingCart.getProducts());
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUserId(userId);
    }
}
