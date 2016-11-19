package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.ReviewDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.ReviewDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.Game;
import ru.kpfu.itis.group.kadyrov.models.Review;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.ReviewService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 16.11.2016.
 */
public class ReviewServiceImpl implements ReviewService {
    ReviewDao reviewDao = new ReviewDaoImpl();

    @Override
    public void addReview(Review review) throws SQLException {
        reviewDao.addReview(review);
    }

    @Override
    public Review findReviewById(int id) {
        return reviewDao.findReviewById(id);
    }

    @Override
    public LinkedList<Review> getAllReviews() {
        return reviewDao.getAllReviews();
    }

    @Override
    public void deleteReview(int id) {
        reviewDao.deleteReview(id);
    }

    @Override
    public Game getGameReviewIsAbout(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT games.* FROM games, reviews WHERE reviews.id = ? AND reviews.game_id = games.id";
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

    @Override
    public User getAuthorOfReview(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT users.* FROM users, reviews WHERE reviews.id = ? AND reviews.creator_id = users.id";
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
