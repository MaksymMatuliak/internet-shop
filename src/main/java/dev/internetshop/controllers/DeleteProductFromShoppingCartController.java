package dev.internetshop.controllers;

import dev.internetshop.lib.Injector;
import dev.internetshop.service.ProductService;
import dev.internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductFromShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String productId = req.getParameter("productId");
        shoppingCartService.deleteProduct(shoppingCartService.getByUserId(userId),
                productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/shopping-cart");
    }
}
