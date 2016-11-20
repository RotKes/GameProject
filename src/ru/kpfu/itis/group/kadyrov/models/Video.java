package ru.kpfu.itis.group.kadyrov.models;

import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Амир on 02.11.2016.
 */
public class Video {
    private int id;
    private int game_id;
    private String videoLink;
    private String title;
    private String date;

    public Video(int id, int game_id, String videoLink, String title, String date) {
        this.id = id;
        this.game_id = game_id;
        this.videoLink = videoLink;
        this.title = title;
        this.date = date;
    }

    public Video(int game_id, String videoLink, String title) {
        this.game_id = game_id;
        this.videoLink = videoLink;
        this.title = title;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public int getId() {
        return id;
    }

    public int getGame_id() {
        return game_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}