package dev.internetshop.controllers;

import dev.internetshop.lib.Injector;
import dev.internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internetshop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String userId = req.getParameter("userId");
        userService.delete(Long.valueOf(userId));
        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }
}
