package ru.kpfu.itis.group.kadyrov.servlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.kpfu.itis.group.kadyrov.models.Game;
import ru.kpfu.itis.group.kadyrov.services.GameService;
import ru.kpfu.itis.group.kadyrov.services.NewsService;
import ru.kpfu.itis.group.kadyrov.services.VideoService;
import ru.kpfu.itis.group.kadyrov.services.implementations.GameServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.NewsServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.implementations.VideoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Амир on 12.11.2016.
 */
@WebServlet(name = "AjaxSearchServlet")
public class AjaxSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String gs = request.getParameter("gs");
        GameService gameService = new GameServiceImpl();
        if (gs != null) {
            try {
                List<Game> list = gameService.getSpecialGames(gs);
                JSONObject jo = new JSONObject();
                jo.put("result", list);
                response.setContentType("application/json");
                response.getWriter().println(jo.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
