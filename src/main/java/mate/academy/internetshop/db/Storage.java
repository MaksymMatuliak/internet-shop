package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

public class Storage {
    private static Long bucketId = 0L;
    private static Long orderId = 0L;
    private static Long productId = 0L;
    private static Long userId = 0L;
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<Product> products = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();

    public static void addProduct(Product product) {
        productId++;
        product.setId(bucketId);
        products.add(product);
    }
}
