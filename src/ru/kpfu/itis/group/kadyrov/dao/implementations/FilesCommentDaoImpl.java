package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.FilesCommentDao;
import ru.kpfu.itis.group.kadyrov.models.CommentFile;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public class FilesCommentDaoImpl implements FilesCommentDao {
    @Override
    public void addFilesComment(CommentFile comment) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && comment != null) {
            String request = "INSERT INTO comment_files (\"post_id\",\"creator_id\",\"text\",\"date\") VALUES (?,?,?,now())";
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
    public CommentFile findFilesCommentById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_files WHERE \"id\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new CommentFile(rs.getInt("id"),
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
    public LinkedList<CommentFile> getAllFilesComments() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_files ORDER BY \"date\"";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<CommentFile> linkedList = new LinkedList<CommentFile>();
                CommentFile comment;
                while (rs.next()) {
                    try {
                        comment = new CommentFile(rs.getInt("id"),
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
    public LinkedList<CommentFile> getAllFilesCommentsOfPostById(int post_id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_files WHERE \"post_id\" = ? ORDER BY \"date\"";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,post_id);
                ResultSet rs = statement.executeQuery();
                LinkedList<CommentFile> linkedList = new LinkedList<CommentFile>();
                CommentFile comment;
                while (rs.next()) {
                    try {
                        comment = new CommentFile(rs.getInt("id"),
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
    public void deleteFilesComment(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM comment_files WHERE id = ? ";
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
