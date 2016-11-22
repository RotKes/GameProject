package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.utils.Helper;
import ru.kpfu.itis.group.kadyrov.models.CommentNews;
import ru.kpfu.itis.group.kadyrov.services.NewsCommentService;
import ru.kpfu.itis.group.kadyrov.services.NewsService;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.NewsCommentServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.NewsServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Амир on 12.11.2016.
 */
@WebServlet(name = "SpecificNewsServlet")
public class SpecificNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String text = request.getParameter("comment_text");
        int post_id = Integer.parseInt(request.getParameter("n"));

        UserService userService = new UserServiceImpl();
        int creator_id = userService.findUser(request.getSession().getAttribute("current_user").toString()).getId();

        NewsCommentService newsCommentService = new NewsCommentServiceImpl();

        try {
            newsCommentService.addNewsComment(new CommentNews(post_id, creator_id, text));
            response.sendRedirect("/topic?n=" + post_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        root.put("news_id", request.getParameter("n"));

        NewsCommentService newsCommentService = new NewsCommentServiceImpl();
        root.put("newsCommentService",newsCommentService);

        NewsService newsService = new NewsServiceImpl();
        root.put("newsService", newsService);

        Helper.render(request, response, "news.ftl", root);
    }
}
