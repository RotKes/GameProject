package ru.kpfu.itis.group.kadyrov.services;

import ru.kpfu.itis.group.kadyrov.models.CommentNews;
import ru.kpfu.itis.group.kadyrov.models.User;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public interface NewsCommentService {
    void addNewsComment(CommentNews comment) throws SQLException;
    CommentNews findNewsCommentById(int id);
    LinkedList<CommentNews> getAllNewsComments();
    LinkedList<CommentNews> getAllNewsCommentsOfPostById(int post_id);
    void deleteNewsComment(int id);
    int getNumberOfCommentsInThisNews(int id);
    User getCreatorOfMessage(int id);
}
