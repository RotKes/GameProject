package ru.kpfu.itis.group.kadyrov.services;

import org.json.JSONArray;
import ru.kpfu.itis.group.kadyrov.models.Game;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 08.11.2016.
 */
public interface GameService {
    void addGame(Game game) throws SQLException;
    Game findGame(String name);
    Game findGameById(String id);
    JSONArray getNamesOfSpecialGames(String q);
    LinkedList<Game> getAllGames();
    void deleteGame(String id);
    void changeRatingOfGame(int game_id);
}
