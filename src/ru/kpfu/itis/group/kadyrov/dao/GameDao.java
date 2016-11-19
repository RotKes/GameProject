package ru.kpfu.itis.group.kadyrov.dao;

import ru.kpfu.itis.group.kadyrov.models.Game;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 05.11.2016.
 */
public interface GameDao {
    void addGame(Game game) throws SQLException;
    Game findGame(String name);
    Game findGameById(int id);
    LinkedList<Game> getAllGames();
    void deleteGame(int id);
}
