package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.VideoCommentDao;
import ru.kpfu.itis.group.kadyrov.models.CommentVideo;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public class VideoCommentDaoImpl implements VideoCommentDao {
    @Override
    public void addVideoComment(CommentVideo comment) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && comment != null) {
            String request = "INSERT INTO comment_videos (\"post_id\",\"creator_id\",\"text\",\"date\") VALUES (?,?,?,DATE_TRUNC('second', NOW()))";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,comment.getPost_id());
                statement.setInt(2,comment.getCreator_id());
                statement.setString(3,comment.getText());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public CommentVideo findVideoCommentById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_videos WHERE \"id\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new CommentVideo(rs.getInt("id"),
                            rs.getInt("post_id"),
                            rs.getInt("creator_id"),
                            rs.getString("text"),
                            rs.getString("date"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<CommentVideo> getAllVideoComments() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_videos ORDER BY \"date\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<CommentVideo> linkedList = new LinkedList<CommentVideo>();
                CommentVideo comment;
                while (rs.next()) {
                    try {
                        comment = new CommentVideo(rs.getInt("id"),
                                rs.getInt("post_id"),
                                rs.getInt("creator_id"),
                                rs.getString("text"),
                                rs.getString("date"));
                    } catch (Exception e) {
                        comment = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(comment);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<CommentVideo> getAllVideoCommentsOfPostById(int post_id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_videos WHERE \"post_id\" = ? ORDER BY \"date\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1, post_id);
                ResultSet rs = statement.executeQuery();
                LinkedList<CommentVideo> linkedList = new LinkedList<CommentVideo>();
                CommentVideo comment;
                while (rs.next()) {
                    try {
                        comment = new CommentVideo(rs.getInt("id"),
                                rs.getInt("post_id"),
                                rs.getInt("creator_id"),
                                rs.getString("text"),
                                rs.getString("date"));
                    } catch (Exception e) {
                        comment = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(comment);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteVideoComment(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM comment_videos WHERE id = ? ";
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
