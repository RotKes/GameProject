package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.UserDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.UserDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.UserService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;
import ru.kpfu.itis.group.kadyrov.utils.Hash;
import ru.kpfu.itis.group.kadyrov.utils.SystemMessage;
import ru.kpfu.itis.group.kadyrov.verifier.MailVerifier;
import ru.kpfu.itis.group.kadyrov.verifier.UserVerifier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Амир on 04.11.2016.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private SystemMessage message;

    @Override
    public SystemMessage addUser(String login, String email, String password, int group_id) {
        if (correctSize(login) && correctSize(password)) {
            User newUser;
            try {
                password = Hash.getMd5Hash(password);
                newUser = new User(group_id, login, email, password);
                if (UserVerifier.checkUserInBD(userDao, login) == null) {
                    if(MailVerifier.checkMailInBD(userDao, email) == null) {
                        userDao.addUser(newUser);
                        return new SystemMessage("user_registration", " зарегистрирован.");
                    }
                    else {
                        return new SystemMessage("email_not_available", "Данный e-mail уже зарегистрирован.");
                    }
                }
                else {
                    return new SystemMessage("user_not_available", "Данный логин используется.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return new SystemMessage("wrong_size","Неверная длина строки.");
        }
        return null;
    }

    @Override
    public User findUser(String login) {
        if (userDao.findUser(login) == null) {
            message = new SystemMessage("user_not_found", "Пользователь не найден.");
            return null;
        } else {
            message = new SystemMessage("user", "Пользователь найден.");
            return userDao.findUser(login);
        }
    }

    @Override
    public User findUserId(String id) {
        if (userDao.findUserId(id) == null) {
            message = new SystemMessage("user_not_found", "Пользователь не найден.");
            return null;
        } else {
            message = new SystemMessage("user", "Пользователь найден.");
            return userDao.findUserId(id);
        }
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteUser(id);
    }

    @Override
    public void changeUsersRules(String id, int group_id) {
        userDao.changeUsersRules(id, group_id);
    }

    private boolean correctSize(String string) {
        return (string.length() >= 2 && string.length() <= 30);
    }

    @Override
    public String getCurrentUserId(String user) {
        if (ConnectionSingleton.getInstance().getConnection() != null && !user.equals("")) {
            String request = "SELECT \"id\" FROM users WHERE \"login\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1, user);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return rs.getString("id");
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
