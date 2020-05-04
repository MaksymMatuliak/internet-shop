package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private ProductService productService = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        userService.create(new User("Maksym", "Maks", "1"));
        userService.create(new User("Ivan", "Iv", "1"));
        userService.create(new User("Leo", "Li", "1"));
        userService.create(new User("Roman", "Roman2000", "1"));
        productService.create(new Product("Pants", new BigDecimal("99.99")));
        productService.create(new Product("Ball", new BigDecimal("25.99")));
        productService.create(new Product("Iphone", new BigDecimal("1099.99")));
        productService.create(new Product("T-Shirt", new BigDecimal("95.99")));
        productService.create(new Product("Glasses", new BigDecimal("149.99")));
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
