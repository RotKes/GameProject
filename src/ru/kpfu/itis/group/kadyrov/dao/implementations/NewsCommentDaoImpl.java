package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.NewsCommentDao;
import ru.kpfu.itis.group.kadyrov.models.CommentNews;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public class NewsCommentDaoImpl implements NewsCommentDao {

    @Override
    public void addNewsComment(CommentNews comment) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && comment != null) {
            String request = "INSERT INTO comment_news (\"post_id\",\"creator_id\",\"text\",\"date\") VALUES (?,?,?,to_char(current_timestamp, 'DD.MM.YYYY, HH24:MI:SS'))";
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
    public CommentNews findNewsCommentById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_news WHERE \"id\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new CommentNews(rs.getInt("id"),
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
    public LinkedList<CommentNews> getAllNewsComments() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_news ORDER BY \"date\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<CommentNews> linkedList = new LinkedList<CommentNews>();
                CommentNews comment;
                while (rs.next()) {
                    try {
                        comment = new CommentNews(rs.getInt("id"),
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
    public LinkedList<CommentNews> getAllNewsCommentsOfPostById(int post_id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_news WHERE \"post_id\" = ? ORDER BY \"date\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,post_id);
                ResultSet rs = statement.executeQuery();
                LinkedList<CommentNews> linkedList = new LinkedList<CommentNews>();
                CommentNews comment;
                while (rs.next()) {
                    try {
                        comment = new CommentNews(rs.getInt("id"),
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
    public void deleteNewsComment(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM comment_news WHERE id = ? ";
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
