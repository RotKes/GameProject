package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.FilesCommentDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.FilesCommentDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.CommentFile;
import ru.kpfu.itis.group.kadyrov.services.FilesCommentService;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public class FilesCommentServiceImpl implements FilesCommentService {
    FilesCommentDao filesCommentDao = new FilesCommentDaoImpl();
    @Override
    public void addFilesComment(CommentFile comment) throws SQLException {
        filesCommentDao.addFilesComment(comment);
    }

    @Override
    public CommentFile findFilesCommentById(int id) {
        return filesCommentDao.findFilesCommentById(id);
    }

    @Override
    public LinkedList<CommentFile> getAllFilesComments() {
        return filesCommentDao.getAllFilesComments();
    }

    @Override
    public LinkedList<CommentFile> getAllFilesCommentsOfPostById(int post_id) {
        return filesCommentDao.getAllFilesCommentsOfPostById(post_id);
    }

    @Override
    public void deleteFilesComment(int id) {
        filesCommentDao.deleteFilesComment(id);
    }
}
