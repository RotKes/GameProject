package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.TopicMessageDao;
import ru.kpfu.itis.group.kadyrov.models.TopicMessages;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 09.11.2016.
 */
public class TopicMessageDaoImpl implements TopicMessageDao {
    @Override
    public void addTopicMessage(TopicMessages topicMessage) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && topicMessage != null) {
            String request = "INSERT INTO messages (\"topic_id\",\"creator_id\",\"text\",\"date\") VALUES (?,?,?,to_char(current_timestamp, 'DD.MM.YYYY, HH24:MI:SS'))";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,topicMessage.getTopic_id());
                statement.setInt(2,topicMessage.getCreator_id());
                statement.setString(3,topicMessage.getText());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public TopicMessages findTopicMessageById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM messages WHERE \"id\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
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
    public LinkedList<TopicMessages> getAllTopicMessages(int topic_id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM \"messages\" WHERE topic_id = ? ORDER BY \"date\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,topic_id);
                ResultSet rs = statement.executeQuery();
                LinkedList<TopicMessages> linkedList = new LinkedList<TopicMessages>();
                TopicMessages topicMessage;
                while (rs.next()) {
                    try {
                        topicMessage = new TopicMessages(rs.getInt("id"),
                                rs.getInt("topic_id"),
                                rs.getInt("creator_id"),
                                rs.getString("text"),
                                rs.getString("date"));
                    } catch (Exception e) {
                        topicMessage = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(topicMessage);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<TopicMessages> getAllMessages() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM \"messages\" ORDER BY \"date\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<TopicMessages> linkedList = new LinkedList<TopicMessages>();
                TopicMessages topicMessage;
                while (rs.next()) {
                    try {
                        topicMessage = new TopicMessages(rs.getInt("id"),
                                rs.getInt("topic_id"),
                                rs.getInt("creator_id"),
                                rs.getString("text"),
                                rs.getString("date"));
                    } catch (Exception e) {
                        topicMessage = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(topicMessage);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteTopicMessage(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM messages WHERE id = ? ";
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
