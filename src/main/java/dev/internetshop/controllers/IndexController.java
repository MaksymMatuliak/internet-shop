package dev.internetshop.controllers;

import dev.internetshop.lib.Injector;
import dev.internetshop.model.Role;
import dev.internetshop.model.User;
import dev.internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internetshop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute("userId");
        if (id == null) {
            req.getRequestDispatcher("/WEB-INF/views/index-for-guests.jsp").forward(req, resp);
            return;
        }
        User user = userService.get(id);
        if (user.getRoles().stream().anyMatch(x -> x.getRoleName().equals(Role.RoleName.ADMIN))) {
            req.getRequestDispatcher("/WEB-INF/views/admin/index-for-admin.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }
    }
}
