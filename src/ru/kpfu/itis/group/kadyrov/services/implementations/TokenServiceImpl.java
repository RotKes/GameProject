package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.TokenDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.TokenDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.TopicMessages;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.services.TokenService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Амир on 04.11.2016.
 */
public class TokenServiceImpl implements TokenService {
    private TokenDao tokenDao;

    public TokenServiceImpl() {
        tokenDao = new TokenDaoImpl();
    }

    public void addToken(String id, String token) {
        tokenDao.addToken(id,token);
    }

    public void updateToken(String id, String token) {
        tokenDao.updateToken(id,token);
    }

    public void deleteToken(String token) {
        tokenDao.deleteToken(token);
    }

    public String findUserByToken(String token) {
        return tokenDao.findUserByToken(token);
    }
}
