package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.TopicMessageDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.TopicMessageDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.TopicMessages;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.TopicMessageService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 09.11.2016.
 */
public class TopicMessageServiceImpl implements TopicMessageService {
    private TopicMessageDao topicMessageDao = new TopicMessageDaoImpl();

    @Override
    public void addTopicMessage(TopicMessages topicMessage) throws SQLException {
        topicMessageDao.addTopicMessage(topicMessage);
    }

    @Override
    public TopicMessages findTopicMessageById(int id) {
        return topicMessageDao.findTopicMessageById(id);
    }

    @Override
    public LinkedList<TopicMessages> getAllTopicMessages(int topic_id) {
        return topicMessageDao.getAllTopicMessages(topic_id);
    }

    @Override
    public LinkedList<TopicMessages> getAllMessages() {
        return topicMessageDao.getAllMessages();
    }

    @Override
    public void deleteTopicMessage(int id) {
        topicMessageDao.deleteTopicMessage(id);
    }
}
