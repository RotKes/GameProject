package ru.kpfu.itis.group.kadyrov.dao;

import ru.kpfu.itis.group.kadyrov.models.TopicMessages;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 09.11.2016.
 */
public interface TopicMessageDao {
    void addTopicMessage(TopicMessages topicMessage) throws SQLException;
    TopicMessages findTopicMessageById(int id);
    LinkedList<TopicMessages> getAllTopicMessages(int topic_id);
    LinkedList<TopicMessages> getAllMessages();
    void deleteTopicMessage(int id);
}
