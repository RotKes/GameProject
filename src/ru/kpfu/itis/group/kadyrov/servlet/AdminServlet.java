package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.services.*;
import ru.kpfu.itis.group.kadyrov.services.implementations.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Амир on 15.11.2016.
 */
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        FilesCommentService filesCommentService = new FilesCommentServiceImpl();
        root.put("filesCommentService",filesCommentService);

        NewsCommentService newsCommentService = new NewsCommentServiceImpl();
        root.put("newsCommentService", newsCommentService);

        GameService gameService = new GameServiceImpl();
        root.put("gameService", gameService);

        NewsService newsService = new NewsServiceImpl();
        root.put("newsService", newsService);

        ReviewCommentService reviewCommentService = new ReviewCommentServiceImpl();
        root.put("reviewCommentService", reviewCommentService);

        VideoCommentService videoCommentService = new VideoCommentServiceImpl();
        root.put("videoCommentService", videoCommentService);

        TopicService topicService = new TopicServiceImpl();
        root.put("topicService", topicService);

        VideoService videoService = new VideoServiceImpl();
        root.put("videoService", videoService);

        TopicMessageService topicMessageService = new TopicMessageServiceImpl();
        root.put("topicMessageService", topicMessageService);

        TokenService tokenService = new TokenServiceImpl();
        root.put("tokenService", tokenService);

        Helper.render(request, response, "admin.ftl", root);
    }
}
