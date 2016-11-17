package ru.kpfu.itis.group.kadyrov.dao;

import ru.kpfu.itis.group.kadyrov.models.Topic;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 09.11.2016.
 */
public interface TopicDao {
    void addTopic(Topic topic) throws SQLException;
    Topic findTopic(String title);
    Topic findTopicById(int id);
    LinkedList<Topic> getAllTopics();
    void deleteTopic(int id);
}
