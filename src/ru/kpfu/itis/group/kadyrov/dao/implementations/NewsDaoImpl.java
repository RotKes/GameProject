package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.NewsDao;
import ru.kpfu.itis.group.kadyrov.models.News;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 10.11.2016.
 */
public class NewsDaoImpl implements NewsDao {
    @Override
    public void addNews(News news) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && news != null) {
            String request = "INSERT INTO news (\"id\",\"game_id\",\"title\",\"text\",\"date\") VALUES (?,?,?,?,now())";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,news.getId());
                statement.setInt(2,news.getGame_id());
                statement.setString(3,news.getTitle());
                statement.setString(4,news.getText());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public News findNewsById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM news WHERE \"id\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new News(rs.getInt("id"),
                            rs.getInt("game_id"),
                            rs.getString("title"),
                            rs.getString("text"),
                            rs.getString("date"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<News> getAllNews() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM news ORDER BY \"date\"";
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

    @Override
    public void deleteNews(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM news WHERE id = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
