package mate.academy.internetshop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthorizationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    private static final String USER_ID = "userId";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/admin/users", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/create-product", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/products", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders", Set.of(Role.RoleName.USER));
        protectedUrls.put("/order-complete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/order/details", Set.of(Role.RoleName.USER));
        protectedUrls.put("/order/delete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/product/delete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/product/add", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/order/complete", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getServletPath();
        if (protectedUrls.get(url) == null) {
            filterChain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User user = userService.get(userId);
        if (isAuthorized(user, protectedUrls.get(url))) {
            filterChain.doFilter(req, resp);
            return;
        } else {
            LOGGER.warn("User who wanted to get access to admin resources" + user);
            req.getRequestDispatcher("/WEB-INF/views/access-denied.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
