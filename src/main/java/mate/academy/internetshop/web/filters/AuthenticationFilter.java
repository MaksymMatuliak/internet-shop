package mate.academy.internetshop.web.filters;

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
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.UserService;

public class AuthenticationFilter implements Filter {
    private static final String USER_ID = "userId";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private Set<String> guestsUrls = new HashSet<>();
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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
        String url = req.getServletPath();
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
