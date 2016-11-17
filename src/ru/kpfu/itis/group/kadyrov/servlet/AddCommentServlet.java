package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.models.*;
import ru.kpfu.itis.group.kadyrov.services.*;
import ru.kpfu.itis.group.kadyrov.services.implementations.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Амир on 14.11.2016.
 */
@WebServlet(name = "AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String text;
        if (request.getParameter("files") != null) {
            text = request.getParameter("files");
            int post_id = Integer.parseInt(request.getParameter("post_id"));

            FilesCommentService filesCommentService = new FilesCommentServiceImpl();

            UserService userService = new UserServiceImpl();
            int user_id = userService.findUser(request.getParameter("current_user")).getId();

            try {
                filesCommentService.addFilesComment(new CommentFile(post_id, user_id, text));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //TODO
            response.sendRedirect("/files");
            return;
        }
        else if (request.getParameter("news") != null) {
            text = request.getParameter("news");
            int post_id = Integer.parseInt(request.getParameter("post_id"));

            NewsCommentService newsCommentService = new NewsCommentServiceImpl();

            UserService userService = new UserServiceImpl();
            int user_id = userService.findUser(request.getParameter("current_user")).getId();

            try {
                newsCommentService.addNewsComment(new CommentNews(post_id, user_id, text));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //TODO
            response.sendRedirect("/news");
            return;
        }
        else if (request.getParameter("reviews") != null) {
            text = request.getParameter("reviews");
            int post_id = Integer.parseInt(request.getParameter("post_id"));

            ReviewCommentService reviewCommentService = new ReviewCommentServiceImpl();

            UserService userService = new UserServiceImpl();
            int user_id = userService.findUser(request.getParameter("current_user")).getId();

            try {
                reviewCommentService.addReviewComment(new CommentReview(post_id, user_id, text));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //TODO
            response.sendRedirect("/reviews");
            return;
        }
        else if(request.getParameter("video") != null) {
            text = request.getParameter("video");
            int post_id = Integer.parseInt(request.getParameter("post_id"));

            VideoCommentService videoCommentService = new VideoCommentServiceImpl();

            UserService userService = new UserServiceImpl();
            int user_id = userService.findUser(request.getParameter("current_user")).getId();

            try {
                videoCommentService.addVideoComment(new CommentVideo(post_id, user_id, text));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //TODO
            response.sendRedirect("/watch?v=" + post_id);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        //Helper.render(request, response, "comment.ftl", root);
    }
}
