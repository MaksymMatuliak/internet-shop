package mate.academy.internetshop.controllers;

import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy");
    private BucketService bucketService = (BucketService) injector.getInstance(BucketService.class);
    private ProductService productService = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String productId = req.getParameter("productId");
        bucketService.addProduct(bucketService.getByUserId(Long.valueOf(1)),
                productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
