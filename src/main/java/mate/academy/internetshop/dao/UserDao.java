package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.model.User;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByLogin(String login);
}
