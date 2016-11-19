package ru.kpfu.itis.group.kadyrov.models;

import java.sql.Date;

/**
 * Created by Амир on 04.11.2016.
 */
public class Topic {
    private int id;
    private int creator_id;
    private String title;
    private String date;

    public Topic(int id, int creator_id, String title, String date) {
        this.id = id;
        this.creator_id = creator_id;
        this.title = title;
        this.date = date;
    }

    public Topic(int creator_id, String title) {
        this.creator_id = creator_id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
