package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order update(Order order) {
        for (Order o: Storage.orders) {
            if (order.getId().equals(o.getId())) {
                o = order;
            }
        }
        return order;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders.removeIf(o -> o.getId().equals(id));
    }

    @Override
    public boolean delete(Order order) {
        return Storage.orders.remove(order);
    }
}