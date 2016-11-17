package ru.kpfu.itis.group.kadyrov.dao;

import ru.kpfu.itis.group.kadyrov.models.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Амир on 02.11.2016.
 */
public interface UserDao {
    void addUser(User user) throws SQLException;
    User findUser(String login);
    User findUserId(String id);
    User findUserByEmail(String email);
    void deleteUser(String id);
    void changeUsersRules(String id, int group_id);
}