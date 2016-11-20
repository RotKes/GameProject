package ru.kpfu.itis.group.kadyrov.utils;

import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 20.11.2016.
 */
public class DataBaseColumns {
    public static LinkedList<String> getColumns(String table_name) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "select column_name from information_schema.columns where table_name= ?";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setString(1,table_name);
                ResultSet rs = statement.executeQuery();
                LinkedList<String> column_names = new LinkedList<>();
                while (rs.next()) {
                    column_names.add(rs.getString("column_name"));
                }
                return column_names;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
