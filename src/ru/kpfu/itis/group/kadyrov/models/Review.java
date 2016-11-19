package ru.kpfu.itis.group.kadyrov.models;

import java.sql.Date;

/**
 * Created by Амир on 04.11.2016.
 */
public class Review {
    private int id;
    private int creator_id;
    private int game_id;
    private String title;
    private String text;
    private int rate;
    private String date;

    public Review(int id, int creator_id, int game_id, String title, String text, int rate, String date) {
        this.id = id;
        this.creator_id = creator_id;
        this.game_id = game_id;
        this.title = title;
        this.text = text;
        this.rate = rate;
        this.date = date;
    }

    public Review(int creator_id, int game_id, String title, String text, int rate) {
        this.creator_id = creator_id;
        this.game_id = game_id;
        this.title = title;
        this.text = text;
        this.rate = rate;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
