package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.utils.Helper;
import ru.kpfu.itis.group.kadyrov.models.Topic;
import ru.kpfu.itis.group.kadyrov.services.TopicService;
import ru.kpfu.itis.group.kadyrov.services.implementations.TopicServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Амир on 07.11.2016.
 */
@WebServlet(name = "ForumServlet")
public class ForumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");

        UserService userService = new UserServiceImpl();
        int creator_id = userService.findUser(request.getSession().getAttribute("current_user").toString()).getId();

        TopicService topicMessageService = new TopicServiceImpl();

        try {
            topicMessageService.addTopic(new Topic(creator_id, title));
            response.sendRedirect("/forum");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        TopicService topicService = new TopicServiceImpl();
        root.put("topicService", topicService);

        LinkedList<Topic> topics = topicService.getAllTopics();
        root.put("all_topics", topics);

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        Helper.render(request, response, "forum-list.ftl", root);
    }
}
