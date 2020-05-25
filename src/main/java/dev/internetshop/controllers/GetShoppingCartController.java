package dev.internetshop.controllers;

import dev.internetshop.lib.Injector;
import dev.internetshop.model.Product;
import dev.internetshop.model.ShoppingCart;
import dev.internetshop.service.ShoppingCartService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        List<Product> allProducts = shoppingCart.getProducts();
        req.setAttribute("products", allProducts);
        req.setAttribute("shoppingCartId", shoppingCart.getId());
        req.getRequestDispatcher("/WEB-INF/views/shopping-cart.jsp").forward(req, resp);
    }
}
