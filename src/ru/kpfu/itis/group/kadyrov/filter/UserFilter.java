package ru.kpfu.itis.group.kadyrov.filter;

import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.TokenService;
import ru.kpfu.itis.group.kadyrov.services.TokenServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Амир on 04.11.2016.
 */
@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Cookie[] cookies = ((HttpServletRequest) req).getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("current_user")) {
                    TokenService tokenService = new TokenServiceImpl();
                    String user_id = tokenService.findUserByToken(cookie.getValue());
                    UserService userService = new UserServiceImpl();
                    User user = userService.findUserId(user_id);
                    if (user != null) {
                        ((HttpServletRequest) req).getSession().setAttribute("current_user", user);
                        ((HttpServletResponse) resp).sendRedirect("/");
                        return;
                    }
                }
            }
        }
        if (((HttpServletRequest) req).getSession().getAttribute("current_user") != null) {
            ((HttpServletResponse)resp).sendRedirect("/");
            return;
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
