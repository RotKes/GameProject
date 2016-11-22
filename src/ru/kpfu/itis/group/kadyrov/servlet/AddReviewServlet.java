package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.utils.Helper;
import ru.kpfu.itis.group.kadyrov.models.Review;
import ru.kpfu.itis.group.kadyrov.services.GameService;
import ru.kpfu.itis.group.kadyrov.services.ReviewService;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.GameServiceImpl;
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
 * Created by Амир on 18.11.2016.
 */
@WebServlet(name = "AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String game = request.getParameter("game");
        String text = request.getParameter("text");
        int rating = Integer.parseInt(request.getParameter("reviewStars"));


        GameService gameService = new GameServiceImpl();
        UserService userService = new UserServiceImpl();
        int user_id = userService.findUser(request.getSession().getAttribute("current_user").toString()).getId();

        ReviewService reviewService = new ReviewServiceImpl();
        try {
            reviewService.addReview(new Review(user_id,gameService.findGame(game).getId(),title,text,rating));
            gameService.changeRatingOfGames();
            response.sendRedirect("/reviews");
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

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        GameService gameService = new GameServiceImpl();
        root.put("all_games", gameService.getAllGames());

        Helper.render(request, response, "add_review.ftl", root);
    }
}
