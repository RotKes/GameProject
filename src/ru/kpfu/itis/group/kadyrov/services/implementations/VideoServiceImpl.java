package ru.kpfu.itis.group.kadyrov.services.implementations;

import org.json.JSONArray;
import ru.kpfu.itis.group.kadyrov.dao.VideoDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.VideoDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.Game;
import ru.kpfu.itis.group.kadyrov.models.Video;
import ru.kpfu.itis.group.kadyrov.services.VideoService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 08.11.2016.
 */
public class VideoServiceImpl implements VideoService {
    VideoDao videoDao = new VideoDaoImpl();
    @Override
    public void addVideo(Video video) throws SQLException {
        videoDao.addVideo(video);
    }

    @Override
    public Video findVideo(String title) {
        return videoDao.findVideo(title);
    }

    @Override
    public Video findVideoById(int id) {
        return videoDao.findVideoById(id);
    }

    @Override
    public JSONArray getTitlesOfSpecialVideos(String q) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT title FROM videos WHERE title LIKE ?  ORDER BY \"date\" DESC, title";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,"%" + q + "%");
                ResultSet rs = statement.executeQuery();
                JSONArray ja = new JSONArray();
                while (rs.next()) {
                    ja.put(rs.getString("title"));
                }
                return ja;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Video> getAllVideos() {
        return videoDao.getAllVideos();
    }

    @Override
    public void deleteVideo(int id) {
        videoDao.deleteVideo(id);
    }

    @Override
    public Game getGameVideoIsAbout(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT games.* FROM games, videos WHERE videos.id = ? AND videos.game_id = games.id";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new Game(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("date"),
                            rs.getString("description"),
                            rs.getString("image"),
                            rs.getInt("rate"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
