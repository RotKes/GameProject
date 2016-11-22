package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.ReviewDao;
import ru.kpfu.itis.group.kadyrov.models.Review;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 16.11.2016.
 */
public class ReviewDaoImpl implements ReviewDao {
    @Override
    public void addReview(Review review) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && review != null) {
            String request = "INSERT INTO reviews (\"game_id\",\"creator_id\",\"title\",\"text\",\"rate\",\"date\") VALUES (?,?,?,?,?,to_char(current_timestamp, 'DD.MM.YYYY, HH24:MI:SS'))";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,review.getGame_id());
                statement.setInt(2,review.getCreator_id());
                statement.setString(3,review.getTitle());
                statement.setString(4,review.getText());
                statement.setInt(5,review.getRate());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Review findReviewById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM reviews WHERE \"id\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new Review(rs.getInt("id"),
                            rs.getInt("game_id"),
                            rs.getInt("creator_id"),
                            rs.getString("title"),
                            rs.getString("text"),
                            rs.getInt("rate"),
                            rs.getString("date"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Review> getAllReviews() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM reviews ORDER BY \"date\"";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<Review> linkedList = new LinkedList<Review>();
                Review review;
                while (rs.next()) {
                    try {
                        review = new Review(rs.getInt("id"),
                                rs.getInt("game_id"),
                                rs.getInt("creator_id"),
                                rs.getString("title"),
                                rs.getString("text"),
                                rs.getInt("rate"),
                                rs.getString("date"));
                    } catch (Exception e) {
                        review = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(review);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteReview(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM reviews WHERE id = ? ";
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
