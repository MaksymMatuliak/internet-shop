package mate.academy.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public Order create(Order element) {
        String query = "INSERT INTO orders (user_id) VALUES (?)";
        String query2 = "INSERT INTO orders_products (product_id, order_id) VALUES (?, ?)";
        Long key;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, element.getUserId());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            key = generatedKeys.getLong(1);
            for (Product product : element.getProducts()) {
                statement = connection.prepareStatement(query2);
                statement.setLong(1, product.getId());
                statement.setLong(2, key);
                statement.execute();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create order in DataBase");
        }
        return get(key).get();
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders "
                + "LEFT JOIN orders_products ON orders.order_id = orders_products.order_id "
                + "LEFT JOIN products ON orders_products.product_id = products.product_id "
                + "WHERE orders.order_id = ?";
        Order order;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            order = getOrderFromResultSet(resultSet);
            order = getProductsFromOrder(order);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get order from DataBase");
        }
        return Optional.of(order);
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(getProductsFromOrder(order));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get orders in DataBase");
        }
        return orders;
    }

    @Override
    public Order update(Order element) {
        String query = "DELETE FROM orders_products WHERE order_id = ?";
        String query2 = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, element.getId());
            statement.execute();
            PreparedStatement statement2 = connection.prepareStatement(query2);
            for (Product product : element.getProducts()) {
                statement2.setLong(1, element.getId());
                statement2.setLong(2, product.getId());
                statement2.execute();
            }
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order in DataBase");
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order in DataBase");
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Long userId = resultSet.getLong("user_id");
        Long orderId = resultSet.getLong("order_id");
        order.setId(orderId);
        order.setUser(userId);
        return order;
    }

    private Order getProductsFromOrder(Order order) throws SQLException {
        String query = "SELECT * FROM orders "
                + "LEFT JOIN orders_products ON orders.order_id = orders_products.order_id "
                + "LEFT JOIN products on products.product_id = orders_products.product_id "
                + "WHERE orders.order_id = ?";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(BigDecimal.valueOf(resultSet.getFloat("price")));
                if (product.getName() != null) {
                    productList.add(product);
                }
            }
            order.setProducts(productList);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get products from order in DataBase");
        }
        return order;
    }
}
