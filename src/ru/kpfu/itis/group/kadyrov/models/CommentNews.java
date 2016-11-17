package ru.kpfu.itis.group.kadyrov.models;

import java.sql.Date;

/**
 * Created by Амир on 04.11.2016.
 */
public class CommentNews {
    private int id;
    private int post_id;
    private int creator_id;
    private String text;
    private String date;

    public CommentNews(int id, int post_id, int creator_id, String text, String date) {
        this.id = id;
        this.post_id = post_id;
        this.creator_id = creator_id;
        this.text = text;
        this.date = date;
    }

    public CommentNews(int id, int post_id, int creator_id, String text) {
        this.id = id;
        this.post_id = post_id;
        this.creator_id = creator_id;
        this.text = text;
    }

    public CommentNews(int post_id, int creator_id, String text) {
        this.post_id = post_id;
        this.creator_id = creator_id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
