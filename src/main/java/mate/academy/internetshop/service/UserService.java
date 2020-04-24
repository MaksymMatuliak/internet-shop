package mate.academy.internetshop.service;

import mate.academy.internetshop.model.User;

public interface UserService extends GenericService<User> {

    User create(User user);

    User update(User user);
}
