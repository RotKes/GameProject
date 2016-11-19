package ru.kpfu.itis.group.kadyrov.servlet;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.TokenService;
import ru.kpfu.itis.group.kadyrov.services.implementations.TokenServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;
import ru.kpfu.itis.group.kadyrov.utils.Hash;
import ru.kpfu.itis.group.kadyrov.utils.SystemMessage;
import ru.kpfu.itis.group.kadyrov.utils.Token;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Амир on 18.10.2016.
 */
public class LoginServlet extends HttpServlet {
    private HttpSession session;
    private Cookie cookie;
    Map<String, Object> root = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String checkCookie = request.getParameter("cookie");
        UserService userService = new UserServiceImpl();
        User currentUser = userService.findUser(login);
        RequestDispatcher rd;

        if (currentUser != null) {
            if (Hash.getMd5Hash(password).equals(currentUser.getPassword())) {
                session = request.getSession();

                //Session
                session.setAttribute("current_user", currentUser);
                if (checkCookie != null && checkCookie.equals("remember-me")) {
                    //Cookie
                    String token = Token.getToken();
                    Cookie cookie = new Cookie("current_user", token);
                    cookie.setMaxAge(6 * 30 * 24 * 60 * 60);
                    response.addCookie(cookie);
                    TokenService tokenService = new TokenServiceImpl();
                    tokenService.addToken("" + currentUser.getId(), token);
                }
                response.sendRedirect("/");
            } else {
                root.put("incorrect_password", "Неверный пароль!");
                request.setAttribute("login", login);
                response.sendRedirect("/login");
            }
        } else {
            root.put("user_not_find", "Пользователь не найден.");
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        session = request.getSession();
        if(session.getAttribute("user_registration") != null) {
            root.put("if_just_registered", "Поздравляем с успешной регистрацией! Войдите, чтобы продолжить!");
            session.removeAttribute("user_registration");
        }
        else {
            if(root.containsKey("if_just_registered"))
                root.remove("if_just_registered");
        }
        root.put("login", request.getParameter("login"));
        Helper.render(request, response, "login.ftl", root);
        root.clear();
    }
}