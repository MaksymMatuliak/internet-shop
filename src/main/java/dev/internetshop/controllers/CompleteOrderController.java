package dev.internetshop.controllers;

import dev.internetshop.lib.Injector;
import dev.internetshop.model.Product;
import dev.internetshop.service.OrderService;
import dev.internetshop.service.ShoppingCartService;
import dev.internetshop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internetshop");
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        List<Product> products = shoppingCartService.getByUserId(userId).getProducts();
        if (!products.isEmpty()) {
            orderService.completeOrder(products, userService.get(userId));
        }
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}
