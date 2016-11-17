package ru.kpfu.itis.group.kadyrov.dao;

/**
 * Created by Амир on 03.11.2016.
 */
public interface TokenDao {
    void addToken(String id, String token);
    void updateToken(String id, String token);
    void deleteToken(String token);
    String findUserByToken(String token);
}
