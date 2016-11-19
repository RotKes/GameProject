package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.VideoCommentDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.VideoCommentDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.CommentVideo;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.VideoCommentService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public class VideoCommentServiceImpl implements VideoCommentService {
    VideoCommentDao videoCommentDao = new VideoCommentDaoImpl();

    @Override
    public void addVideoComment(CommentVideo comment) throws SQLException {
        videoCommentDao.addVideoComment(comment);
    }

    @Override
    public CommentVideo findVideoCommentById(int id) {
        return videoCommentDao.findVideoCommentById(id);
    }

    @Override
    public LinkedList<CommentVideo> getAllVideoComments() {
        return videoCommentDao.getAllVideoComments();
    }

    @Override
    public LinkedList<CommentVideo> getAllVideoCommentsOfPostById(int post_id) {
        return videoCommentDao.getAllVideoCommentsOfPostById(post_id);
    }

    @Override
    public void deleteVideoComment(int id) {
        videoCommentDao.deleteVideoComment(id);
    }

    @Override
    public int getNumberOfCommentsInThisVideo(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT COUNT(*) AS c FROM comment_videos GROUP BY post_id HAVING post_id = ?";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return rs.getInt("c");
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public User getCreatorOfMessage(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT users.* FROM users, comment_videos WHERE comment_videos.id = ? AND comment_videos.creator_id = users.id";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new User(rs.getString("id"),
                            rs.getInt("group_id"),
                            rs.getString("login"),
                            rs.getString("e-mail"),
                            rs.getString("password"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
