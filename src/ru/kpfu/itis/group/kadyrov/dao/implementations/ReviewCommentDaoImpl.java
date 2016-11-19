package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.ReviewCommentDao;
import ru.kpfu.itis.group.kadyrov.models.CommentReview;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public class ReviewCommentDaoImpl implements ReviewCommentDao {
    @Override
    public void addReviewComment(CommentReview comment) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && comment != null) {
            String request = "INSERT INTO comment_reviews (\"post_id\",\"creator_id\",\"text\",\"date\") VALUES (?,?,?,DATE_TRUNC('second', NOW()))";
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
    public CommentReview findReviewCommentById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_reviews WHERE \"id\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new CommentReview(rs.getInt("id"),
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
    public LinkedList<CommentReview> getAllReviewComments() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_reviews ORDER BY \"date\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<CommentReview> linkedList = new LinkedList<CommentReview>();
                CommentReview comment;
                while (rs.next()) {
                    try {
                        comment = new CommentReview(rs.getInt("id"),
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
    public LinkedList<CommentReview> getAllReviewCommentsOfPostById(int post_id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comment_reviews WHERE \"post_id\" = ? ORDER BY \"date\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,post_id);
                ResultSet rs = statement.executeQuery();
                LinkedList<CommentReview> linkedList = new LinkedList<CommentReview>();
                CommentReview comment;
                while (rs.next()) {
                    try {
                        comment = new CommentReview(rs.getInt("id"),
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
    public void deleteReviewComment(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM comment_reviews WHERE id = ? ";
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
