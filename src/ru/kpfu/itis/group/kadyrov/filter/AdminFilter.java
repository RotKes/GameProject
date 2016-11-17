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
@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Object session = ((HttpServletRequest) req).getSession().getAttribute("current_user");
        Cookie[] cookies = ((HttpServletRequest) req).getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("current_user")) {

                    TokenService tokenService = new TokenServiceImpl();
                    UserService userService = new UserServiceImpl();

                    String user_id = tokenService.findUserByToken(cookie.getValue());
                    if (user_id == null) {
                        ((HttpServletResponse) resp).sendError(403, "Cookie были изменены или не существуют.");
                    }

                    User user = null;
                    try {
                        userService.findUserId(user_id);
                        user = userService.findUserId(user_id);
                    } catch (Exception e) {
                        ((HttpServletResponse) resp).sendError(403, "Данный пользователь не найден.");
                    }

                    if (user != null) {
                        if (session == null)
                            ((HttpServletRequest) req).getSession().setAttribute("current_user", user);
                        else {
                            User current_session = (User) session;
                            if (!("" + current_session.getId()).equals(user_id)) {
                                cookie.setMaxAge(0);
                                cookie.setValue("");
                                ((HttpServletResponse) resp).addCookie(cookie);
                                ((HttpServletRequest)req).getSession().invalidate();
                                ((HttpServletResponse) resp).sendError(403, "Вы используете чужие cookie!");
                                return;
                            } else {
                                if (user.getGroup_id() == 2) {
                                    chain.doFilter(req, resp);
                                    return;
                                } else {
                                    ((HttpServletResponse) resp).sendRedirect("/");
                                    return;
                                }
                            }
                        }
                    } else {
                        ((HttpServletResponse) resp).sendError(403, "Пользователь не найден!");
                        return;
                    }
                }
            }
        }
        if (session == null) {
            ((HttpServletResponse) resp).sendRedirect("/login");
            return;
        } else {
            if (((User) session).getGroup_id() == 2) {
                chain.doFilter(req, resp);
                return;
            } else {
                ((HttpServletResponse) resp).sendRedirect("/");
                return;
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
