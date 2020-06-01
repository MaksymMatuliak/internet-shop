package dev.internetshop.service;

import dev.internetshop.model.User;
import java.util.Optional;

public interface UserService extends GenericService<User> {

    User create(User user);

    User update(User user);

    Optional<User> findByLogin(String login);
}
