package ru.kpfu.itis.group.kadyrov.filter;

import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.TokenService;
import ru.kpfu.itis.group.kadyrov.services.implementations.TokenServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Амир on 03.11.2016.
 */
@WebFilter(filterName = "GuestFilter")
public class GuestFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Object session = ((HttpServletRequest) servletRequest).getSession().getAttribute("current_user");
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("current_user")) {

                    TokenService tokenService = new TokenServiceImpl();
                    UserService userService = new UserServiceImpl();

                    String user_id = tokenService.findUserByToken(cookie.getValue());
                    if (user_id == null) {
                        ((HttpServletResponse) servletResponse).sendError(403, "Cookie были изменены или не существуют.");
                        return;
                    }

                    User user = null;
                    try {
                        userService.findUserId(user_id);
                        user = userService.findUserId(user_id);
                    } catch (Exception e) {
                        ((HttpServletResponse) servletResponse).sendError(403, "Данный пользователь не найден");
                        return;
                    }

                    if (user != null) {
                        if (session == null)
                            ((HttpServletRequest) servletRequest).getSession().setAttribute("current_user", user);
                        else {
                            User current_session = (User) session;
                            if (!("" + current_session.getId()).equals(user_id)) {
                                cookie.setMaxAge(0);
                                cookie.setValue("");
                                ((HttpServletResponse) servletResponse).addCookie(cookie);
                                ((HttpServletRequest)servletRequest).getSession().invalidate();
                                ((HttpServletResponse) servletResponse).sendError(403, "Вы используете чужие cookie!");
                                return;
                            } else {
                                filterChain.doFilter(servletRequest, servletResponse);
                                return;
                            }
                        }
                    } else {
                        ((HttpServletResponse) servletResponse).sendError(403, "Пользователь не найден!");
                        return;
                    }
                }
            }
        }

        // TODO
        if (session == null && !((HttpServletRequest) servletRequest).getRequestURI().equals("/")) {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }

}
