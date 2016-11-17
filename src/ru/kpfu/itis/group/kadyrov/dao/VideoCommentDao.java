package ru.kpfu.itis.group.kadyrov.dao;

import ru.kpfu.itis.group.kadyrov.models.CommentVideo;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public interface VideoCommentDao {
    void addVideoComment(CommentVideo comment) throws SQLException;
    CommentVideo findVideoCommentById(int id);
    LinkedList<CommentVideo> getAllVideoComments();
    LinkedList<CommentVideo> getAllVideoCommentsOfPostById(int post_id);
    void deleteVideoComment(int id);
}
