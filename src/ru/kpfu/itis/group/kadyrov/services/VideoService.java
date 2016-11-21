package ru.kpfu.itis.group.kadyrov.services;

import org.json.JSONArray;
import ru.kpfu.itis.group.kadyrov.models.Game;
import ru.kpfu.itis.group.kadyrov.models.Video;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 08.11.2016.
 */
public interface VideoService {
    void addVideo(Video video) throws SQLException;
    Video findVideo(String title);
    Video findVideoById(int id);
    LinkedList<Video> getAllVideos();
    void deleteVideo(int id);
    Game getGameVideoIsAbout(int id);
}
