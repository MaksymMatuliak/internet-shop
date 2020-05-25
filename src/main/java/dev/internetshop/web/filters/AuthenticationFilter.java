package dev.internetshop.web.filters;

import dev.internetshop.lib.Injector;
import dev.internetshop.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private static final String USER_ID = "userId";
    private static final Injector INJECTOR = Injector.getInstance("dev.internetshop");
    private Set<String> guestsUrls = new HashSet<>();
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) {
        guestsUrls.add("/login");
        guestsUrls.add("/registration");
        guestsUrls.add("/products");
        guestsUrls.add("/");
        guestsUrls.add("/admin/inject");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        var url = req.getServletPath();
        if (guestsUrls.contains(url)) {
            filterChain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null || userService.get(userId) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
