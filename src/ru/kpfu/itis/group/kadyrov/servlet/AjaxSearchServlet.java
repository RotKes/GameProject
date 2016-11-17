package ru.kpfu.itis.group.kadyrov.servlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.kpfu.itis.group.kadyrov.services.GameService;
import ru.kpfu.itis.group.kadyrov.services.implementations.GameServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Амир on 12.11.2016.
 */
@WebServlet(name = "AjaxSearchServlet")
public class AjaxSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String q = request.getParameter("q");
        GameService gameService = new GameServiceImpl();
        JSONObject jo = new JSONObject();
        JSONArray ja;
        if (q != null) {
            try {
                if (!q.equals(""))
                    ja = gameService.getNamesOfSpecialGames(q);
                else
                    ja = new JSONArray();
                jo.put("result", ja);
                response.setContentType("text/json");
                response.getWriter().println(jo.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
