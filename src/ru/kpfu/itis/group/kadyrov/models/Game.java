package ru.kpfu.itis.group.kadyrov.models;

/**
 * Created by Амир on 04.11.2016.
 */
public class Game {
    private int id;
    private String name;
    private String date;
    private String description;
    private String imageURL;
    private int rating;

    public Game(int id, String name, String date, String description, String imageURL, int rating) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.imageURL = imageURL;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
