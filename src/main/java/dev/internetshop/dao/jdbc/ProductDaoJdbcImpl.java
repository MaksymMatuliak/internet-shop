package dev.internetshop.dao.jdbc;

import dev.internetshop.dao.ProductDao;
import dev.internetshop.exceptions.DataProcessingException;
import dev.internetshop.lib.Dao;
import dev.internetshop.model.Product;
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
public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public Product create(Product element) {
        String query = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            element.setId(generatedKeys.getLong(1));
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create product in DataBase", e);
        }
        return element;
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        Product product;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            product = getProductFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get product from DataBase", e);
        }
        return Optional.of(product);
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get products in DataBase", e);
        }
        return products;
    }

    @Override
    public Product update(Product element) {
        String query = "UPDATE products SET name = ?, price = ? WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.setLong(3, element.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update product in DataBase", e);
        }
        return element;
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete product in DataBase", e);
        }
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        Long productId = resultSet.getLong("product_id");
        String productName = resultSet.getString("name");
        BigDecimal productPrice = resultSet.getBigDecimal("price");
        product.setId(productId);
        product.setName(productName);
        product.setPrice(productPrice);
        return product;
    }
}
