package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.NewsCommentDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.NewsCommentDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.CommentNews;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.NewsCommentService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @Override
    public int getNumberOfCommentsInThisNews(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT COUNT(*) AS c FROM comment_news GROUP BY post_id HAVING post_id = ?";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return rs.getInt("c");
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public User getCreatorOfMessage(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT users.* FROM users, comment_news WHERE comment_news.id = ? AND comment_news.creator_id = users.id";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new User(rs.getString("id"),
                            rs.getInt("group_id"),
                            rs.getString("login"),
                            rs.getString("e-mail"),
                            rs.getString("password"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
