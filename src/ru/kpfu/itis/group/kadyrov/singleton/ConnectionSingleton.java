package ru.kpfu.itis.group.kadyrov.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Амир on 18.10.2016.
 */
public class ConnectionSingleton {
    private static Connection conn = null;
    private static PreparedStatement statement;

    private static ConnectionSingleton instance = new ConnectionSingleton();

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/newgameproject";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String DRIVER = "org.postgresql.Driver";
    private ConnectionSingleton() {
        if(conn == null) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static ConnectionSingleton getInstance() {
        return instance;
    }

    public static PreparedStatement getStatement() {
        return statement;
    }

    public static void setStatement(PreparedStatement statement) {
        ConnectionSingleton.statement = statement;
    }
}