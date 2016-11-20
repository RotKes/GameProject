package ru.kpfu.itis.group.kadyrov.dao;

import ru.kpfu.itis.group.kadyrov.models.Group;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 20.11.2016.
 */
public interface GroupDao {
    void addGroup(Group group) throws SQLException;
    Group findGroupById(int id);
    LinkedList<Group> getAllGroups();
    void deleteGroup(int id);
}
