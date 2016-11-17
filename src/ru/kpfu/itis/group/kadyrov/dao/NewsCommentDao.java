package ru.kpfu.itis.group.kadyrov.dao;

import ru.kpfu.itis.group.kadyrov.models.CommentNews;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public interface NewsCommentDao {
    void addNewsComment(CommentNews comment) throws SQLException;
    CommentNews findNewsCommentById(int id);
    LinkedList<CommentNews> getAllNewsComments();
    LinkedList<CommentNews> getAllNewsCommentsOfPostById(int post_id);
    void deleteNewsComment(int id);
}
