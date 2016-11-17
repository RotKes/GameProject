package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.VideoCommentDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.VideoCommentDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.CommentVideo;
import ru.kpfu.itis.group.kadyrov.services.VideoCommentService;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public class VideoCommentServiceImpl implements VideoCommentService {
    VideoCommentDao videoCommentDao = new VideoCommentDaoImpl();

    @Override
    public void addVideoComment(CommentVideo comment) throws SQLException {
        videoCommentDao.addVideoComment(comment);
    }

    @Override
    public CommentVideo findVideoCommentById(int id) {
        return videoCommentDao.findVideoCommentById(id);
    }

    @Override
    public LinkedList<CommentVideo> getAllVideoComments() {
        return videoCommentDao.getAllVideoComments();
    }

    @Override
    public LinkedList<CommentVideo> getAllVideoCommentsOfPostById(int post_id) {
        return videoCommentDao.getAllVideoCommentsOfPostById(post_id);
    }

    @Override
    public void deleteVideoComment(int id) {
        videoCommentDao.deleteVideoComment(id);
    }
}
