package mate.academy.internetshop.dao.impl;

import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.Item;

public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public Item update(Item item) {
        for (Item i: Storage.items) {
            if (item.getId() == i.getId()) {
                i = item;
            }
        }
        return item;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.items.remove(get(id));
    }

    @Override
    public boolean delete(Item item) {
        return Storage.items.remove(item);
    }
}
