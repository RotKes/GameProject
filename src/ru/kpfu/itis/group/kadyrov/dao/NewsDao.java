package ru.kpfu.itis.group.kadyrov.dao;

import ru.kpfu.itis.group.kadyrov.models.News;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 10.11.2016.
 */
public interface NewsDao {
    void addNews(News news) throws SQLException;
    News findNewsById(int id);
    LinkedList<News> getAllNews();
    void deleteNews(int id);
}
