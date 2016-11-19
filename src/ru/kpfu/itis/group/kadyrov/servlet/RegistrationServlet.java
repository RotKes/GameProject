package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;
import ru.kpfu.itis.group.kadyrov.utils.SystemMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Амир on 19.10.2016.
 */
@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private SystemMessage message;
    private HttpSession session;
    private Map<String, Object> root = new HashMap<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String login = request.getParameter("username");
        String email = request.getParameter("e-mail");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        message = userService.addUser(login, email, password, 1);

        root.put(message.getName(),message.getMessage());
        if(message.getName().equals("user_registration")) {
            session = request.getSession();
            session.setAttribute(message.getName(),message.getMessage());
            response.sendRedirect("/login");
        }
        else {
            response.sendRedirect("/registration");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        root.put("current_user", request.getSession().getAttribute("current_user"));
        Helper.render(request, response, "registration.ftl", root);
        root.clear();
    }
}
