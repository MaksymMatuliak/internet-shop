package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE users.login = ?";
        User user;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = getUserFromResultSet(resultSet);
            getRolesForUser(user);
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public User create(User element) {
        String insertInUsers =
                "INSERT INTO users (name, login, password, salt) VALUES (?, ?, ?, ?)";
        String insertInUserRoles = "INSERT INTO user_roles (user_id, role_id) VALUES (?, 1);";
        Long key;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(insertInUsers, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            statement.setString(2, element.getLogin());
            statement.setString(3, element.getPassword());
            statement.setBytes(4, element.getSalt());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            key = generatedKeys.getLong(1);
            element.setId(key);
            PreparedStatement statement2 = connection.prepareStatement(insertInUserRoles);
            statement2.setLong(1, key);
            statement2.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create user in DataBase", e);
        }
        return element;
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users INNER JOIN user_roles "
                + "ON users.user_id = user_roles.user_id "
                + "WHERE users.user_id = ?";
        User user;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = getUserFromResultSet(resultSet);
            getRolesForUser(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user from DataBase", e);
        }
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get users in DataBase", e);
        }
        return users;
    }

    @Override
    public User update(User element) {
        String query = "UPDATE users SET name = ?, login = ?, password = ?  WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, element.getName());
            statement.setString(2, element.getLogin());
            statement.setString(3, element.getPassword());
            statement.setLong(4, element.getId());
            statement.executeUpdate();
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update user in DataBase", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete user in DataBase", e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        Long userId = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        byte[] salt = resultSet.getBytes("salt");
        user.setId(userId);
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setSalt(salt);
        return user;
    }

    private User getRolesForUser(User user) {
        String query = "SELECT * FROM user_roles "
                + "INNER JOIN roles on user_roles.role_id = roles.role_id "
                + "WHERE user_roles.user_id = ?";
        Set<Role> roleSet = new HashSet<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = Role.of(resultSet.getString("role_name"));
                role.setId(resultSet.getLong("role_id"));
                roleSet.add(role);
            }
            user.setRoles(roleSet);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user from DataBase", e);
        }
        return user;
    }
}
