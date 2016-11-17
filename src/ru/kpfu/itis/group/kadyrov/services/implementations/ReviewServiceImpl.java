package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.ReviewDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.ReviewDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.Review;
import ru.kpfu.itis.group.kadyrov.services.ReviewService;

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
}
