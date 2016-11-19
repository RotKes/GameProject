package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.models.CommentVideo;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.VideoCommentService;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.VideoService;
import ru.kpfu.itis.group.kadyrov.services.implementations.VideoCommentServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.VideoServiceImpl;

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
 * Created by Амир on 07.11.2016.
 */
@WebServlet(name = "VideoDescripionServlet")
public class VideoDescriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String text = request.getParameter("comment_text");
        int post_id = Integer.parseInt(request.getParameter("v"));

        VideoCommentService videoCommentService = new VideoCommentServiceImpl();

        UserService userService = new UserServiceImpl();
        int user_id = userService.findUser(request.getSession().getAttribute("current_user").toString()).getId();

        try {
            videoCommentService.addVideoComment(new CommentVideo(post_id, user_id, text));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/watch?v=" + post_id);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        root.put("video_id", request.getParameter("v"));

        VideoCommentService videoCommentService = new VideoCommentServiceImpl();
        root.put("videoCommentService", videoCommentService);

        VideoService videoService = new VideoServiceImpl();
        root.put("videoService", videoService);

        Helper.render(request, response, "video-description.ftl", root);
    }
}
