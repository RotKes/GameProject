package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.utils.AddImage;
import ru.kpfu.itis.group.kadyrov.utils.Helper;
import ru.kpfu.itis.group.kadyrov.models.Game;
import ru.kpfu.itis.group.kadyrov.services.GameService;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.GameServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;
import ru.kpfu.itis.group.kadyrov.utils.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Амир on 21.11.2016.
 */
@WebServlet(name = "AddGameServlet")
@MultipartConfig
public class AddGameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String date = request.getParameter("pickyDate");
        String text = request.getParameter("text");
        String image = null;

        if (request.getPart("image").getSize()!=0) {
            Part filePart = request.getPart("image");
            image = Token.getToken()+".jpg";
            AddImage.add(filePart, image);
        }

        GameService gameService = new GameServiceImpl();

        try {
            gameService.addGame(new Game(name, date, text, "http://localhost:8080/files/" + image));
            response.sendRedirect("/games");
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

        Helper.render(request, response, "add_game.ftl", root);
    }
}
