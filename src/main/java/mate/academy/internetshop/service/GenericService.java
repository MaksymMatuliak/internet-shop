package mate.academy.internetshop.service;

import java.util.List;

public interface GenericService<T> {

    T get(Long id);

    List<T> getAll();

    boolean delete(Long id);
}
