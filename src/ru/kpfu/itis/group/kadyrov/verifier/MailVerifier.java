package ru.kpfu.itis.group.kadyrov.verifier;

import ru.kpfu.itis.group.kadyrov.dao.UserDao;
import ru.kpfu.itis.group.kadyrov.models.User;

/**
 * Created by Амир on 04.11.2016.
 */
public class MailVerifier {
    public static User checkMailInBD(UserDao userDao, String email) {
        return userDao.findUserByEmail(email);
    }
}
