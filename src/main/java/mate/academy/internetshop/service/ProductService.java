package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Product;

public interface ProductService extends GenericService<Product> {

    Product create(Product product);

    Product update(Product product);
}
