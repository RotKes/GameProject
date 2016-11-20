package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.UserDao;
import ru.kpfu.itis.group.kadyrov.models.User;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.*;
import java.util.List;

/**
 * Created by Амир on 02.11.2016.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && user != null) {
            String request = "INSERT INTO users (\"id\",\"group_id\",\"login\",\"e-mail\",\"password\") VALUES (?,?,?,?,?) ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,user.getId());
                statement.setInt(2,user.getGroup_id());
                statement.setString(3,user.getLogin());
                statement.setString(4,user.getEmail());
                statement.setString(5,user.getPassword());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findUser(String login) {
        if (ConnectionSingleton.getInstance().getConnection()!= null && !login.equals("")) {
            String request = "SELECT * FROM users WHERE login = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,login);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new User(rs.getInt("id"),
                            rs.getInt("group_id"),
                            rs.getString("login"),
                            rs.getString("e-mail"),
                            rs.getString("password"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User findUserId(String id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null && !id.equals("")) {
            String request = "SELECT * FROM users WHERE id = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new User(rs.getString("id"),
                            rs.getInt("group_id"),
                            rs.getString("login"),
                            rs.getString("e-mail"),
                            rs.getString("password"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        if (ConnectionSingleton.getInstance().getConnection() != null && !email.equals("")) {
            String request = "SELECT * FROM users WHERE \"e-mail\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1, email);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new User(rs.getString("id"),
                            rs.getInt("group_id"),
                            rs.getString("login"),
                            rs.getString("e-mail"),
                            rs.getString("password"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteUser(String id) {
        if (ConnectionSingleton.getInstance().getConnection() != null && !id.equals("")) {
            String request = "DELETE FROM users WHERE id = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void changeUsersRules(String id, int group_id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null && !id.equals("")) {
            String request = "UPDATE users SET group_id = ? WHERE id = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,group_id);
                statement.setString(2,id);
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }
}