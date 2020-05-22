package mate.academy.internetshop.dao;

import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getUserOrders(User user);
}
