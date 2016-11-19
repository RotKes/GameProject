package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.models.Review;
import ru.kpfu.itis.group.kadyrov.services.ReviewService;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.ReviewServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.VideoServiceImpl;

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
 * Created by Амир on 17.11.2016.
 */
@WebServlet(name = "ReviewsServlet")
public class ReviewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        ReviewService reviewService = new ReviewServiceImpl();
        root.put("reviewService", reviewService);

        LinkedList<Review> reviews = reviewService.getAllReviews();
        root.put("all_reviews", reviews);

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        Helper.render(request, response, "review-list.ftl", root);
    }
}
