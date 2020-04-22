package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Item;
import java.util.List;

public interface ItemService {

    Item create(Item item);

    Item get(Long id);

    Item update(Item item);

    boolean delete(Long id);

    boolean delete(Item item);

    List<Item> getAll();

    List<Item> getAllAvailable();
}
