package ru.kpfu.itis.group.kadyrov.servlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

        if(request.getParameter("gs") != null) {
            String gs = request.getParameter("gs");
            GameService gameService = new GameServiceImpl();
            JSONObject jo = new JSONObject();
            JSONArray ja;
            if (gs != null) {
                try {
                    if (!gs.equals(""))
                        ja = gameService.getNamesOfSpecialGames(gs);
                    else
                        ja = new JSONArray();
                    jo.put("result", ja);
                    response.setContentType("text/json");
                    response.getWriter().println(jo.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            request.removeAttribute("gs");
        }
        else if(request.getParameter("ns") != null) {
            String ns = request.getParameter("ns");
            NewsService newsService = new NewsServiceImpl();
            JSONObject jo = new JSONObject();
            JSONArray ja;
            if (ns != null) {
                try {
                    if (!ns.equals(""))
                        ja = newsService.getSpecialTitles(ns);
                    else
                        ja = new JSONArray();
                    jo.put("result", ja);
                    response.setContentType("text/json");
                    response.getWriter().println(jo.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            request.removeAttribute("ns");
        }
        else if (request.getParameter("vs") != null) {
            String vs = request.getParameter("vs");
            VideoService videoService = new VideoServiceImpl();
            JSONObject jo = new JSONObject();
            JSONArray ja;
            if (vs != null) {
                try {
                    if (!vs.equals(""))
                        ja = videoService.getTitlesOfSpecialVideos(vs);
                    else
                        ja = new JSONArray();
                    jo.put("result", ja);
                    response.setContentType("text/json");
                    response.getWriter().println(jo.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            request.removeAttribute("vs");
        }
    }
}
