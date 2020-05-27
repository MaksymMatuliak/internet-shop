package dev.internetshop.dao.jdbc;

import dev.internetshop.dao.OrderDao;
import dev.internetshop.exceptions.DataProcessingException;
import dev.internetshop.lib.Dao;
import dev.internetshop.model.Order;
import dev.internetshop.model.Product;
import dev.internetshop.model.User;
import dev.internetshop.util.ConnectionUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public Order create(Order element) {
        String insertInOrders = "INSERT INTO orders (user_id) VALUES (?)";
        String insertInOrdersProducts =
                "INSERT INTO orders_products (product_id, order_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(insertInOrders,
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement statement2 =
                        connection.prepareStatement(insertInOrdersProducts)) {
            statement.setLong(1, element.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            Long key = generatedKeys.getLong(1);
            element.setId(key);
            for (Product product : element.getProducts()) {
                statement2.setLong(1, product.getId());
                statement2.setLong(2, key);
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create order in DataBase", e);
        }
        return element;
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders "
                + "INNER JOIN orders_products ON orders.order_id = orders_products.order_id "
                + "WHERE orders.order_id = ?";
        Order order;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            order = getOrderFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get order from DataBase", e);
        }
        return Optional.of(getOrderWithProducts(order));
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(getOrderWithProducts(order));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get orders in DataBase", e);
        }
        return orders;
    }

    @Override
    public Order update(Order element) {
        String delete = "DELETE FROM orders_products WHERE order_id = ?";
        String insert = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(delete);
                PreparedStatement statement2 = connection.prepareStatement(insert)) {
            statement.setLong(1, element.getId());
            statement.executeUpdate();
            for (Product product : element.getProducts()) {
                statement2.setLong(1, element.getId());
                statement2.setLong(2, product.getId());
                statement2.executeUpdate();
            }
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order in DataBase", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order in DataBase", e);
        }
    }

    @Override
    public List<Order> getUserOrders(User user) {
        String query = "SELECT * FROM orders WHERE orders.user_id = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(getOrderWithProducts(order));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get orders in DataBase", e);
        }
        return orders;
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Long userId = resultSet.getLong("user_id");
        Long orderId = resultSet.getLong("order_id");
        order.setId(orderId);
        order.setUser(userId);
        return order;
    }

    private Order getOrderWithProducts(Order order) {
        String query = "SELECT * FROM orders_products "
                + "INNER JOIN products on products.product_id = orders_products.product_id "
                + "WHERE orders_products.order_id = ?";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(BigDecimal.valueOf(
                        Math.round(resultSet.getFloat("price") * 100.0) / 100.0));
                if (product.getName() != null) {
                    productList.add(product);
                }
            }
            order.setProducts(productList);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get products from order in DataBase", e);
        }
        return order;
    }
}
