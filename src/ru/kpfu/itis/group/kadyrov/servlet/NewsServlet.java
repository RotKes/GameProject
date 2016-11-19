package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.models.News;
import ru.kpfu.itis.group.kadyrov.services.NewsService;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.NewsServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Амир on 05.11.2016.
 */
@WebServlet(name = "NewsServlet")
public class NewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        NewsService newsService = new NewsServiceImpl();
        root.put("newsService", newsService);

        LinkedList<News> videos = newsService.getAllNews();
        root.put("all_news", videos);

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        Helper.render(request, response, "news-list.ftl", root);
    }
}
