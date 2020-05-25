package dev.internetshop.controllers;

import dev.internetshop.lib.Injector;
import dev.internetshop.model.Role;
import dev.internetshop.model.ShoppingCart;
import dev.internetshop.model.User;
import dev.internetshop.service.ShoppingCartService;
import dev.internetshop.service.UserService;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internetshop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        if (name.length() == 0 || login.length() == 0 || password.length() == 0) {
            req.setAttribute("message", "Fields cannot be empty!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            return;
        }
        if (userService.findByLogin(login).isPresent()) {
            req.setAttribute("message", "User with such login already exists!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            return;
        }
        if (password.equals(passwordRepeat)) {
            User user = new User();
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setRoles(Set.of(Role.of("USER")));
            shoppingCartService.create(
                    new ShoppingCart(userService.create(user).getId(), new LinkedList<>()));
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("message", "Passwords are not the same!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
