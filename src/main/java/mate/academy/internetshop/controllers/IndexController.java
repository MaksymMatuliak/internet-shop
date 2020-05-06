package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class IndexController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute("userId");
        if (id == null) {
            req.getRequestDispatcher("/WEB-INF/views/indexForGuests.jsp").forward(req, resp);
            return;
        }
        User user = userService.get(id);
        if (user.getRoles().stream().anyMatch(x -> x.getRoleName().equals(Role.RoleName.ADMIN))) {
            req.getRequestDispatcher("/WEB-INF/views/admin/indexForAdmin.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }
    }
}
