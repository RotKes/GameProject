package ru.kpfu.itis.group.kadyrov.utils;

/**
 * Created by Амир on 04.11.2016.
 */
public class SystemMessage {
    private String name;
    private String message;

    public SystemMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
