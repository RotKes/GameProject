package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.dao.TopicDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.TopicDaoImpl;
import ru.kpfu.itis.group.kadyrov.dao.TopicMessageDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.TopicMessageDaoImpl;
import ru.kpfu.itis.group.kadyrov.services.*;
import ru.kpfu.itis.group.kadyrov.services.implementations.TopicMessageServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.TopicServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Амир on 07.11.2016.
 */
@WebServlet(name = "ForumPageServlet")
public class ForumPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));
        root.put("theme", request.getParameter("id"));
        TopicService topicService = new TopicServiceImpl();
        TopicMessageService topicMessageService = new TopicMessageServiceImpl();
        root.put("all_messages", topicMessageService.getAllTopicMessages(Integer.parseInt(request.getParameter("id"))));
        root.put("topicService", topicService);
        root.put("topicMessageService", topicMessageService);

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        Helper.render(request, response, "forum-theme.ftl", root);
    }
}
