package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.TokenDao;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Амир on 03.11.2016.
 */
public class TokenDaoImpl implements TokenDao {
    public void addToken(String user_id, String token) {
        if (ConnectionSingleton.getInstance().getConnection() != null && !user_id.equals("") && !token.equals("")) {
            String request = "INSERT INTO cookies (user_id,token) VALUES ( ? , ? )";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,user_id);
                statement.setString(2,token);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateToken(String user_id, String token) {
        if (ConnectionSingleton.getInstance().getConnection() != null && !user_id.equals("") && !token.equals("")) {
            String request = "UPDATE cookies SET token = ? WHERE user_id = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,token);
                statement.setString(2,user_id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteToken(String token) {
        if (ConnectionSingleton.getInstance().getConnection() != null && !token.equals("")) {
            String request = "DELETE FROM cookies WHERE token = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,token);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String findUserByToken(String token) {
        if (ConnectionSingleton.getInstance().getConnection() != null && !token.equals("")) {
            String request = "SELECT * FROM cookies WHERE token= ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);;
                statement.setString(1,token);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return rs.getString("user_id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
