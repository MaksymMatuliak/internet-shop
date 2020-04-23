package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.products.add(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.products.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        for (Product p: Storage.products) {
            if (product.getId() == p.getId()) {
                p = product;
            }
        }
        return product;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.products.remove(get(id));
    }

    @Override
    public boolean delete(Product product) {
        return Storage.products.remove(product);
    }
}
