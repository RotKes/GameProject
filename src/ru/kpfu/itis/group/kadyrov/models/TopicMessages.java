package ru.kpfu.itis.group.kadyrov.models;

import java.sql.Date;

/**
 * Created by Амир on 04.11.2016.
 */
public class TopicMessages {
    private int id;
    private int topic_id;
    private int creator_id;
    private String text;
    private String date;

    public TopicMessages(int id, int topic_id, int creator_id, String text, String date) {
        this.id = id;
        this.topic_id = topic_id;
        this.creator_id = creator_id;
        this.text = text;
        this.date = date;
    }

    public TopicMessages(int topic_id, int creator_id, String text) {
        this.topic_id = topic_id;
        this.creator_id = creator_id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
