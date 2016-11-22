package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.VideoDao;
import ru.kpfu.itis.group.kadyrov.models.Video;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 08.11.2016.
 */
public class VideoDaoImpl implements VideoDao {
    @Override
    public void addVideo(Video video) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && video != null) {
            String request = "INSERT INTO videos (\"game_id\",\"video_link\",\"title\",\"date\") VALUES (?,?,?,to_char(current_timestamp, 'DD.MM.YYYY, HH24:MI:SS'))";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,video.getGame_id());
                statement.setString(2,video.getVideoLink());
                statement.setString(3,video.getTitle());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Video findVideo(String title) {
        if (ConnectionSingleton.getInstance().getConnection()!= null && !title.equals("")) {
            String request = "SELECT * FROM videos WHERE \"title\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,title);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new Video(rs.getInt("id"),
                            rs.getInt("game_id"),
                            rs.getString("video_link"),
                            rs.getString("title"),
                            rs.getString("date"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Video findVideoById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
        String request = "SELECT * FROM videos WHERE \"id\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new Video(rs.getInt("id"),
                            rs.getInt("game_id"),
                            rs.getString("video_link"),
                            rs.getString("title"),
                            rs.getString("date"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Video> getAllVideos() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM videos ORDER BY \"date\"";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<Video> linkedList = new LinkedList<Video>();
                Video video;
                while (rs.next()) {
                    try {
                        video = new Video(rs.getInt("id"),
                                rs.getInt("game_id"),
                                rs.getString("video_link"),
                                rs.getString("title"),
                                rs.getString("date"));
                    } catch (Exception e) {
                        video = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(video);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteVideo(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM videos WHERE id = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
