package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User admin = new User("Maksym", "admin", "");
        admin.setRoles(Set.of(Role.of("ADMIN"), Role.of("USER")));
        shoppingCartService.create(
                new ShoppingCart(userService.create(admin), new LinkedList<Product>()));
        User userIvan = new User("Ivan", "Iv", "1234");
        shoppingCartService.create(
                new ShoppingCart(userService.create(userIvan), new LinkedList<Product>()));
        User userLeo = new User("Leo", "L2000", "11223344");
        shoppingCartService.create(
                new ShoppingCart(userService.create(userLeo), new LinkedList<Product>()));
        productService.create(new Product("Pants", new BigDecimal("99.99")));
        productService.create(new Product("Ball", new BigDecimal("25.99")));
        productService.create(new Product("Iphone", new BigDecimal("1099.99")));
        productService.create(new Product("T-Shirt", new BigDecimal("95.99")));
        productService.create(new Product("Glasses", new BigDecimal("149.99")));
        req.getRequestDispatcher("/WEB-INF/views/indexForGuests.jsp").forward(req, resp);
    }
}
