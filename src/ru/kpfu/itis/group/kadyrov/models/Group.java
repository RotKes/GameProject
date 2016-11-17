package ru.kpfu.itis.group.kadyrov.models;

/**
 * Created by Амир on 04.11.2016.
 */
public class Group {
    private int id;
    private String name;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
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
}
