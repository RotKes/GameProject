package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.NewsDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.NewsDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.News;
import ru.kpfu.itis.group.kadyrov.services.NewsService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 10.11.2016.
 */
public class NewsServiceImpl implements NewsService {
    NewsDao newsDao = new NewsDaoImpl();

    @Override
    public void addNews(News news) throws SQLException {
        newsDao.addNews(news);
    }

    @Override
    public News findNewsById(int id) {
        return newsDao.findNewsById(id);
    }

    @Override
    public LinkedList<News> getAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public void deleteNews(int id) {
        newsDao.deleteNews(id);
    }

    @Override
    public LinkedList<News> getLastThreeNews() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM news ORDER BY \"date\" DESC LIMIT 3";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<News> linkedList = new LinkedList<News>();
                News news;
                while (rs.next()) {
                    try {
                        news = new News(rs.getInt("id"),
                                rs.getInt("game_id"),
                                rs.getString("title"),
                                rs.getString("text"),
                                rs.getString("date"));
                    } catch (Exception e) {
                        news = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(news);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
