package ru.kpfu.itis.group.kadyrov.services;

import org.json.JSONArray;
import ru.kpfu.itis.group.kadyrov.models.Topic;
import ru.kpfu.itis.group.kadyrov.models.TopicMessages;
import ru.kpfu.itis.group.kadyrov.models.User;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 09.11.2016.
 */
public interface TopicService {
    void addTopic(Topic topic) throws SQLException;
    Topic findTopic(String title);
    Topic findTopicById(int id);
    JSONArray getTitlesOfSpecialTopics(String q);
    LinkedList<Topic> getAllTopics();
    void deleteTopic(int id);
    TopicMessages getLastMessageOfTopic(int topic_id);
    User getCreatorOfMessage(int id);
    int getNumberOfMessagesInTopic(int id);
}
