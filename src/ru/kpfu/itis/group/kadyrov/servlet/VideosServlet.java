package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.models.Video;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.services.implementations.UserServiceImpl;
import ru.kpfu.itis.group.kadyrov.services.VideoService;
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
 * Created by Амир on 07.11.2016.
 */
@WebServlet(name = "VideosServlet")
public class VideosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        VideoService videoService = new VideoServiceImpl();
        root.put("videoService", videoService);

        LinkedList<Video> videos = videoService.getAllVideos();
        root.put("all_videos", videos);

        UserService userService = new UserServiceImpl();
        root.put("userService", userService);

        Helper.render(request, response, "Video.ftl", root);
    }
}
