package dev.internetshop.controllers;

import dev.internetshop.lib.Injector;
import dev.internetshop.service.ProductService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internetshop");
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String productId = req.getParameter("productId");
        productService.delete(Long.valueOf(productId));
        resp.sendRedirect(req.getContextPath() + "/admin/products");
    }
}
