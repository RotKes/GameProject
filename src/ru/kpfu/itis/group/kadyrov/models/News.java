package ru.kpfu.itis.group.kadyrov.models;

import java.sql.Date;

/**
 * Created by Амир on 04.11.2016.
 */
public class News {
    private int id;
    private int game_id;
    private String title;
    private String text;
    private String date;

    public News(int id, int game_id, String title, String text, String date) {
        this.id = id;
        this.game_id = game_id;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
