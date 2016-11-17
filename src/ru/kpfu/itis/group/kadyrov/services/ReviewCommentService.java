package ru.kpfu.itis.group.kadyrov.services;

import ru.kpfu.itis.group.kadyrov.models.CommentReview;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public interface ReviewCommentService {
    void addReviewComment(CommentReview comment) throws SQLException;
    CommentReview findReviewCommentById(int id);
    LinkedList<CommentReview> getAllReviewComments();
    LinkedList<CommentReview> getAllReviewCommentsOfPostById(int post_id);
    void deleteReviewComment(int id);
}
