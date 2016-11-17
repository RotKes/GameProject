package ru.kpfu.itis.group.kadyrov.verifier;

import ru.kpfu.itis.group.kadyrov.dao.UserDao;
import ru.kpfu.itis.group.kadyrov.models.User;

/**
 * Created by Амир on 04.11.2016.
 */
public class UserVerifier {
    public static User checkUserInBD(UserDao userDao, String login) {
        return userDao.findUser(login);
    }
}
