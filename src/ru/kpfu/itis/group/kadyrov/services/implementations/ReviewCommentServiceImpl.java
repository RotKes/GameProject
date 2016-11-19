package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.ReviewCommentDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.ReviewCommentDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.CommentReview;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.ReviewCommentService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public class ReviewCommentServiceImpl implements ReviewCommentService {
    ReviewCommentDao reviewCommentDao = new ReviewCommentDaoImpl();

    @Override
    public void addReviewComment(CommentReview comment) throws SQLException {
        reviewCommentDao.addReviewComment(comment);
    }

    @Override
    public CommentReview findReviewCommentById(int id) {
        return reviewCommentDao.findReviewCommentById(id);
    }

    @Override
    public LinkedList<CommentReview> getAllReviewComments() {
        return reviewCommentDao.getAllReviewComments();
    }

    @Override
    public LinkedList<CommentReview> getAllReviewCommentsOfPostById(int post_id) {
        return reviewCommentDao.getAllReviewCommentsOfPostById(post_id);
    }

    @Override
    public void deleteReviewComment(int id) {
        reviewCommentDao.deleteReviewComment(id);
    }

    @Override
    public int getNumberOfCommentsInThisReview(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT COUNT(*) AS c FROM comment_reviews GROUP BY post_id HAVING post_id = ?";
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
            String request = "SELECT users.* FROM users, comment_reviews WHERE comment_reviews.id = ? AND comment_reviews.creator_id = users.id";
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
