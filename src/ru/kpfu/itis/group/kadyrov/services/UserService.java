package ru.kpfu.itis.group.kadyrov.services;

import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.utils.SystemMessage;

import java.sql.SQLException;

/**
 * Created by Амир on 03.11.2016.
 */
public interface UserService {
    SystemMessage addUser(String login, String email, String password, int group_id);
    User findUser(String login);
    User findUserId(String id);
    void deleteUser(String id);
    void changeUsersRules(String id, int group_id);
    String getCurrentUserId(String user);
}
