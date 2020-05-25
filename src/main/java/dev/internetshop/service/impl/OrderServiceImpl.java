package dev.internetshop.service.impl;

import dev.internetshop.dao.OrderDao;
import dev.internetshop.lib.Inject;
import dev.internetshop.lib.Service;
import dev.internetshop.model.Order;
import dev.internetshop.model.Product;
import dev.internetshop.model.User;
import dev.internetshop.service.OrderService;
import dev.internetshop.service.ShoppingCartService;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        List<Product> product = new ArrayList<>(products);
        shoppingCartService.clear(shoppingCartService.getByUserId(user.getId()));
        return orderDao.create(new Order(user.getId(), product));
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
