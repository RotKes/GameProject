package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.NewsCommentDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.NewsCommentDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.CommentNews;
import ru.kpfu.itis.group.kadyrov.services.NewsCommentService;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 14.11.2016.
 */
public class NewsCommentServiceImpl implements NewsCommentService {
    NewsCommentDao newsCommentDao = new NewsCommentDaoImpl();

    @Override
    public void addNewsComment(CommentNews comment) throws SQLException {
        newsCommentDao.addNewsComment(comment);
    }

    @Override
    public CommentNews findNewsCommentById(int id) {
        return newsCommentDao.findNewsCommentById(id);
    }

    @Override
    public LinkedList<CommentNews> getAllNewsComments() {
        return newsCommentDao.getAllNewsComments();
    }

    @Override
    public LinkedList<CommentNews> getAllNewsCommentsOfPostById(int post_id) {
        return newsCommentDao.getAllNewsCommentsOfPostById(post_id);
    }

    @Override
    public void deleteNewsComment(int id) {
        newsCommentDao.deleteNewsComment(id);
    }
}
