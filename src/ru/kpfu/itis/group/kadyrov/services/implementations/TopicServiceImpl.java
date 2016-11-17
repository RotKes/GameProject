package ru.kpfu.itis.group.kadyrov.services.implementations;

import org.json.JSONArray;
import ru.kpfu.itis.group.kadyrov.dao.TopicDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.TopicDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.Topic;
import ru.kpfu.itis.group.kadyrov.models.TopicMessages;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.TopicService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 09.11.2016.
 */
public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao = new TopicDaoImpl();
    @Override
    public void addTopic(Topic topic) throws SQLException {
        topicDao.addTopic(topic);
    }

    @Override
    public Topic findTopic(String title) {
        return topicDao.findTopic(title);
    }

    @Override
    public Topic findTopicById(int id) {
        return topicDao.findTopicById(id);
    }

    @Override
    public JSONArray getTitlesOfSpecialTopics(String q) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT title FROM topics WHERE title LIKE ?  ORDER BY \"date\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,"%" + q + "%");
                ResultSet rs = statement.executeQuery();
                JSONArray ja = new JSONArray();
                while (rs.next()) {
                    ja.put(rs.getString("title"));
                }
                return ja;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }

    @Override
    public void deleteTopic(int id) {
        topicDao.deleteTopic(id);
    }

    @Override
    public TopicMessages getLastMessageOfTopic(int topic_id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT messages.* FROM topics, messages WHERE topics.id = ? AND messages.topic_id = topics.id ORDER BY \"date\" DESC LIMIT 1";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1, topic_id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new TopicMessages(rs.getInt("id"),
                            rs.getInt("topic_id"),
                            rs.getInt("creator_id"),
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
    public User getCreatorOfMessage(int message_id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT users.* FROM users, messages WHERE messages.id = ? AND messages.creator_id = users.id";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1, message_id);
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

    @Override
    public int getNumberOfMessagesInTopic(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT COUNT(*) AS c FROM messages GROUP BY topic_id HAVING topic_id = ?";
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
}
