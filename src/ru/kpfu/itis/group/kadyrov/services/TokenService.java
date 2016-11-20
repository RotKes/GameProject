package ru.kpfu.itis.group.kadyrov.services;

import ru.kpfu.itis.group.kadyrov.models.Cookie;
import ru.kpfu.itis.group.kadyrov.models.TopicMessages;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.utils.Token;

import java.util.LinkedList;

/**
 * Created by Амир on 03.11.2016.
 */
public interface TokenService {
    void addToken(String id, String token);
    void updateToken(String id, String token);
    void deleteToken(String token);
    LinkedList<Cookie> getAllTokens();
    String findUserByToken(String token);
}
