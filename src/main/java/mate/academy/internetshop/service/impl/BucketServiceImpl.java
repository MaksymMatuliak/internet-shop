package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private BucketDao bucketDao;

    @Override
    public Bucket addProduct(Bucket bucket, Product product) {
        bucket.getProducts().add(product);
        return bucketDao.update(bucket);
    }

    @Override
    public boolean deleteProduct(Bucket bucket, Product product) {
        boolean remove = bucket.getProducts().remove(product);
        bucketDao.update(bucket);
        return remove;
    }

    @Override
    public void clear(Bucket bucket) {
        bucket.getProducts().removeAll(bucket.getProducts());
    }

    @Override
    public Bucket getByUserId(Long userId) {
        return bucketDao.getAll().stream()
                .filter(b -> b.getUser().getId().equals(userId))
                .findFirst()
                .get();
    }

    @Override
    public List<Product> getAllProducts(Bucket bucket) {
        return bucketDao.getAll().stream()
                .filter(b -> b.getId().equals(bucket.getId()))
                .findFirst()
                .get()
                .getProducts();
    }
}
