package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.TopicDao;
import ru.kpfu.itis.group.kadyrov.models.Topic;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 09.11.2016.
 */
public class TopicDaoImpl implements TopicDao {
    @Override
    public void addTopic(Topic topic) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && topic != null) {
            String request = "INSERT INTO videos (\"id\",\"creator_id\",\"title\",\"date\") VALUES (?,?,?,now())";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,topic.getId());
                statement.setInt(2,topic.getCreator_id());
                statement.setString(3,topic.getTitle());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Topic findTopic(String title) {
        if (ConnectionSingleton.getInstance().getConnection()!= null && !title.equals("")) {
            String request = "SELECT * FROM topics WHERE \"title\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,title);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new Topic(rs.getInt("id"),
                            rs.getInt("creator_id"),
                            rs.getString("title"),
                            rs.getString("date"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Topic findTopicById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM topics WHERE \"id\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new Topic(rs.getInt("id"),
                            rs.getInt("creator_id"),
                            rs.getString("title"),
                            rs.getString("date"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Topic> getAllTopics() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM \"topics\" ORDER BY \"date\"";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<Topic> linkedList = new LinkedList<Topic>();
                Topic topic;
                while (rs.next()) {
                    try {
                        topic = new Topic(rs.getInt("id"),
                                rs.getInt("creator_id"),
                                rs.getString("title"),
                                rs.getString("date"));
                    } catch (Exception e) {
                        topic = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(topic);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteTopic(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM topics WHERE id = ? ";
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
