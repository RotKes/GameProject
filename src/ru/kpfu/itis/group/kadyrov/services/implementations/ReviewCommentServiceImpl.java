package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.ReviewCommentDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.ReviewCommentDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.CommentReview;
import ru.kpfu.itis.group.kadyrov.services.ReviewCommentService;

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
}
