package ru.kpfu.itis.group.kadyrov.services;

import org.json.JSONArray;
import ru.kpfu.itis.group.kadyrov.models.Game;
import ru.kpfu.itis.group.kadyrov.models.News;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 10.11.2016.
 */
public interface NewsService {
    void addNews(News news) throws SQLException;
    News findNewsById(int id);
    LinkedList<News> getAllNews();
    void deleteNews(int id);
    LinkedList<News> getLastThreeNews();
    Game getGameNewsIsAbout(int id);
}
