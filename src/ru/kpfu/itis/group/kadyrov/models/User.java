package ru.kpfu.itis.group.kadyrov.models;

/**
 * Created by Амир on 02.11.2016.
 */
public class User {
    private int id;
    private int group_id;
    private String login;
    private String email;
    private String password;

    public User(String id, int group_id, String login, String email, String password) {
        this.id = Integer.parseInt(id);
        this.group_id = group_id;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(int group_id, String login, String email, String password) {
        this.group_id = group_id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.id = (login + email + password).hashCode();
    }

    public int getId() {
        return id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return getLogin();
    }
}
