package ru.kpfu.itis.group.kadyrov.services.implementations;

import org.json.JSONArray;
import ru.kpfu.itis.group.kadyrov.dao.GameDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.GameDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.Game;
import ru.kpfu.itis.group.kadyrov.services.GameService;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 08.11.2016.
 */
public class GameServiceImpl implements GameService {
    private GameDao gameDao = new GameDaoImpl();
    @Override
    public void addGame(Game game) throws SQLException {
        gameDao.addGame(game);
    }

    @Override
    public Game findGame(String name) {
        return gameDao.findGame(name);
    }

    @Override
    public Game findGameById(int id) {
        return gameDao.findGameById(id);
    }

    @Override
    public JSONArray getNamesOfSpecialGames(String q) {
        if (ConnectionSingleton.getInstance().getConnection()!= null && !q.equals("")) {
            String request = "SELECT \"name\" FROM games WHERE name LIKE ? ORDER BY \"name\"";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,"%" + q + "%");
                ResultSet rs = statement.executeQuery();
                JSONArray ja = new JSONArray();
                while (rs.next()) {
                    ja.put(rs.getString("name"));
                }
                return ja;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public void deleteGame(int id) {
        gameDao.deleteGame(id);
    }

    @Override
    public void changeRatingOfGame(int game_id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "UPDATE games SET rate = (SELECT AVG(rate) FROM reviews WHERE reviews.game_id = ?) WHERE games.id = ?";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,game_id);
                statement.setInt(2,game_id);
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }
}
