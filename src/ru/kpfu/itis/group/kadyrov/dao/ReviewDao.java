package ru.kpfu.itis.group.kadyrov.dao;

import ru.kpfu.itis.group.kadyrov.models.Review;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 16.11.2016.
 */
public interface ReviewDao {
    void addReview(Review review) throws SQLException;
    Review findReviewById(int id);
    LinkedList<Review> getAllReviews();
    void deleteReview(int id);
}
