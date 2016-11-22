package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.GameDao;
import ru.kpfu.itis.group.kadyrov.models.Game;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 05.11.2016.
 */
public class GameDaoImpl implements GameDao {
    @Override
    public void addGame(Game game) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && game != null) {
            String request = "INSERT INTO games (\"name\",\"date\",\"description\",\"image\") VALUES (?,?,?,?) ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,game.getName());
                statement.setString(2,game.getDate());
                statement.setString(3,game.getDescription());
                statement.setString(4,game.getImageURL());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Game findGame(String name) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM games WHERE \"name\" = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,name);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new Game(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("date"),
                            rs.getString("description"),
                            rs.getString("image"),
                            rs.getFloat("rate"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Game findGameById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM games WHERE id = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new Game(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("date"),
                            rs.getString("description"),
                            rs.getString("image"),
                            rs.getFloat("rate"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Game> getAllGames() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM games ORDER BY \"name\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<Game> linkedList = new LinkedList<Game>();
                Game game;
                while (rs.next()) {
                    try {
                        game = new Game(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("date"),
                                rs.getString("description"),
                                rs.getString("image"),
                                rs.getFloat("rate"));
                    } catch (Exception e) {
                        game = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(game);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteGame(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM games WHERE id = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
