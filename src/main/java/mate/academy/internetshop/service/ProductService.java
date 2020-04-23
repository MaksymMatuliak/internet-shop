package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Product;
import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product get(Long id);

    Product update(Product product);

    boolean delete(Long id);

    boolean delete(Product product);

    List<Product> getAll();
}
