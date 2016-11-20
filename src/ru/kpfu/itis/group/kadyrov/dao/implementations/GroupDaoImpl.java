package ru.kpfu.itis.group.kadyrov.dao.implementations;

import ru.kpfu.itis.group.kadyrov.dao.GroupDao;
import ru.kpfu.itis.group.kadyrov.singleton.ConnectionSingleton;


import ru.kpfu.itis.group.kadyrov.models.Group;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 20.11.2016.
 */
public class GroupDaoImpl implements GroupDao {
    @Override
    public void addGroup(Group group) throws SQLException {
        if (ConnectionSingleton.getInstance().getConnection() != null && group != null) {
            String request = "INSERT INTO groups (\"id\",\"name\") VALUES (?,?) ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,group.getId());
                statement.setString(2,group.getName());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Group findGroupById(int id) {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM groups WHERE id = ? ";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    return new Group(rs.getInt("id"),
                            rs.getString("name"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Group> getAllGroups() {
        if (ConnectionSingleton.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM groups ORDER BY \"name\" DESC";
            try {
                PreparedStatement statement = ConnectionSingleton.getConnection().prepareStatement(request);
                ResultSet rs = statement.executeQuery();
                LinkedList<Group> linkedList = new LinkedList<Group>();
                Group group;
                while (rs.next()) {
                    try {
                        group = new Group(rs.getInt("id"),
                                rs.getString("name"));
                    } catch (Exception e) {
                        group = null;
                        e.printStackTrace();
                    }
                    linkedList.addFirst(group);
                }
                return linkedList;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteGroup(int id) {
        if (ConnectionSingleton.getInstance().getConnection() != null) {
            String request = "DELETE FROM groups WHERE id = ? ";
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
