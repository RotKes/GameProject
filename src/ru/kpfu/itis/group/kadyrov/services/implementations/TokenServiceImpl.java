package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.TokenDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.TokenDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.Cookie;
import ru.kpfu.itis.group.kadyrov.services.TokenService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;
import ru.kpfu.itis.group.kadyrov.utils.Token;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

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

    @Override
    public LinkedList<Cookie> getAllTokens() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM \"cookies\"";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<Cookie> linkedList = new LinkedList<Cookie>();
                Cookie cookie;
                while (rs.next()) {
                    try {
                        cookie = new Cookie(rs.getString("user_id"),
                                rs.getString("token"));
                    } catch (Exception e) {
                        cookie = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(cookie);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
