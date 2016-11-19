package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.models.CommentReview;
import ru.kpfu.itis.group.kadyrov.models.Review;
import ru.kpfu.itis.group.kadyrov.services.ReviewCommentService;
import ru.kpfu.itis.group.kadyrov.services.ReviewService;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.ReviewCommentServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.ReviewServiceImpl;
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
 * Created by Амир on 17.11.2016.
 */
@WebServlet(name = "SpecificReviewServlet")
public class SpecificReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String text = request.getParameter("comment_text");
        int post_id = Integer.parseInt(request.getParameter("r"));

        ReviewCommentService reviewCommentService = new ReviewCommentServiceImpl();

        UserService userService = new UserServiceImpl();
        int user_id = userService.findUser(request.getSession().getAttribute("current_user").toString()).getId();

        try {
            reviewCommentService.addReviewComment(new CommentReview(post_id, user_id, text));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/review?r=" + post_id);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        root.put("review_id", request.getParameter("r"));

        ReviewCommentService reviewCommentService = new ReviewCommentServiceImpl();
        root.put("reviewCommentService", reviewCommentService);

        ReviewService reviewService = new ReviewServiceImpl();
        root.put("reviewService", reviewService);

        Helper.render(request, response, "review.ftl", root);
    }
}
