package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;

public interface BucketService {

    Bucket addProduct(Bucket bucket, Product product);

    boolean deleteProduct(Bucket bucket, Product product);

    void clear(Bucket bucket);

    Bucket getByUserId(Long userId);

    List<Product> getAllProducts(Bucket bucket);
}
