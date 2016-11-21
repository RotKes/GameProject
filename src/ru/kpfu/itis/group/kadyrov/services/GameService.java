package ru.kpfu.itis.group.kadyrov.services;

import org.json.JSONArray;
import ru.kpfu.itis.group.kadyrov.models.Game;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Амир on 08.11.2016.
 */
public interface GameService {
    void addGame(Game game) throws SQLException;
    Game findGame(String name);
    Game findGameById(int id);
    List<Game> getSpecialGames(String q);
    LinkedList<Game> getAllGames();
    void deleteGame(int id);
    void changeRatingOfGame(int game_id);
}
