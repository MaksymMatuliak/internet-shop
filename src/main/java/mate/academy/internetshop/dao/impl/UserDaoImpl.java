package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        for (User u: Storage.users) {
            if (u.getId().equals(u.getId())) {
                u = user;
            }
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users.removeIf(u -> u.getId().equals(id));
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }
}
