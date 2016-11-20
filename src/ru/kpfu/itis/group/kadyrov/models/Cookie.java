package ru.kpfu.itis.group.kadyrov.models;

/**
 * Created by Амир on 20.11.2016.
 */
public class Cookie {
    String user_id;
    String token;

    public Cookie(String user_id, String token) {
        this.user_id = user_id;
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getToken() {
        return token;
    }
}
