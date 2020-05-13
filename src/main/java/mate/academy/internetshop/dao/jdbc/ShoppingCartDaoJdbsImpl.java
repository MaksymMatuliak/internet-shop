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
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class ShoppingCartDaoJdbsImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart element) {
        String query = "INSERT INTO shopping_carts (user_id) VALUES (?)";
        Long key;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, element.getUserId());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            key = generatedKeys.getLong(1);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create shopping cart in DataBase");
        }
        return get(key).get();
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        String query = "SELECT * FROM shopping_carts "
                + "LEFT JOIN shopping_carts_products "
                + "ON shopping_carts.shopping_cart_id = shopping_carts_products.shopping_cart_id "
                + "LEFT JOIN products "
                + "ON shopping_carts_products.product_id = products.product_id "
                + "WHERE shopping_carts.shopping_cart_id = ?";
        ShoppingCart shoppingCart;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            shoppingCart = getShoppingCartFromResultSet(resultSet);
            shoppingCart = getProductsForShoppingCart(shoppingCart);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get shopping cart from DataBase");
        }
        return Optional.of(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getAll() {
        String query = "SELECT * FROM shopping_carts";
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet);
                shoppingCarts.add(getProductsForShoppingCart(shoppingCart));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get users in DataBase");
        }
        return shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart element) {
        String query = "DELETE FROM shopping_carts_products WHERE shopping_cart_id = ?";
        String query2 = "INSERT INTO shopping_carts_products (shopping_cart_id, product_id) "
                + "VALUES (?, ?)";
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
            throw new DataProcessingException("Can't update shopping cart in DataBase");
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM shopping_carts WHERE shopping_cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete shopping cart in DataBase");
        }
    }

    private ShoppingCart getShoppingCartFromResultSet(ResultSet resultSet) throws SQLException {
        ShoppingCart shoppingCart = new ShoppingCart();
        Long userId = resultSet.getLong("user_id");
        Long shoppingCartId = resultSet.getLong("shopping_cart_id");
        shoppingCart.setId(shoppingCartId);
        shoppingCart.setUser(userId);
        return shoppingCart;
    }

    private ShoppingCart getProductsForShoppingCart(ShoppingCart shoppingCart) throws SQLException {
        String query = "SELECT * FROM shopping_carts_products "
                + "LEFT JOIN products ON products.product_id = shopping_carts_products.product_id "
                + "WHERE shopping_carts_products.shopping_cart_id = ?";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCart.getId());
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
            shoppingCart.setProducts(productList);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get shopping cart from DataBase");
        }
        return shoppingCart;
    }
}
