package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.models.Game;
import ru.kpfu.itis.group.kadyrov.models.News;
import ru.kpfu.itis.group.kadyrov.services.GameService;
import ru.kpfu.itis.group.kadyrov.services.NewsService;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.GameServiceImpl;
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
 * Created by Амир on 17.11.2016.
 */
@WebServlet(name = "AddNewsServlet")
public class AddNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String header = request.getParameter("header");
        String game = request.getParameter("game");
        String text = request.getParameter("text");

        GameService gameService = new GameServiceImpl();

        NewsService newsService = new NewsServiceImpl();
        try {
            newsService.addNews(new News(gameService.findGame(game).getId(),header,text));
            response.sendRedirect("/news");
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

        Helper.render(request, response, "add_news.ftl", root);
    }
}
