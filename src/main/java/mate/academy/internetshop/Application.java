package mate.academy.internetshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product product = new Product("Iphone", new BigDecimal(1000));
        Product product2 = new Product("Car", new BigDecimal(200600));
        Product product3 = new Product("Apple", new BigDecimal(5));
        productService.create(product);
        productService.create(product2);
        productService.create(product3);
        product2.setPrice(new BigDecimal(100_000));
        productService.update(product2);
        List<Product> listOfProducts = productService.getAll();
        for (Product p : listOfProducts) {
            System.out.println(p.toString());
        }
        UserService userService = (UserService) injector.getInstance(UserService.class);
        BucketService bucketService = (BucketService) injector.getInstance(BucketService.class);
        User userMaks = new User("Maksym");
        Bucket bucketOfMaks = new Bucket(userMaks, new ArrayList<>());
        userService.create(userMaks);
        userService.getAll().stream().forEach(System.out::println);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        Order orderOfMaksym = new Order(userMaks, listOfProducts);
    }
}
