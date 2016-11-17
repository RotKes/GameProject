package ru.kpfu.itis.group.kadyrov.services;

import ru.kpfu.itis.group.kadyrov.models.CommentFile;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public interface FilesCommentService {
    void addFilesComment(CommentFile comment) throws SQLException;
    CommentFile findFilesCommentById(int id);
    LinkedList<CommentFile> getAllFilesComments();
    LinkedList<CommentFile> getAllFilesCommentsOfPostById(int post_id);
    void deleteFilesComment(int id);
}
