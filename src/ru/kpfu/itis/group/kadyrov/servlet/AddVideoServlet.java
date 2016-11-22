package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.utils.Helper;
import ru.kpfu.itis.group.kadyrov.models.Video;
import ru.kpfu.itis.group.kadyrov.services.GameService;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.VideoService;
import ru.kpfu.itis.group.kadyrov.services.implementations.GameServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;
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
 * Created by Амир on 17.11.2016.
 */
@WebServlet(name = "AddVideoServlet")
public class AddVideoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String game = request.getParameter("game");
        String link = request.getParameter("link");
        String title = request.getParameter("title");

        GameService gameService = new GameServiceImpl();

        VideoService videoService = new VideoServiceImpl();
        try {
            videoService.addVideo(new Video(gameService.findGame(game).getId(),link,title));
            response.sendRedirect("/videos");
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

        Helper.render(request, response, "add_video.ftl", root);
    }
}
