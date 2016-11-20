package ru.kpfu.itis.group.kadyrov.services.implementations;

import ru.kpfu.itis.group.kadyrov.dao.GroupDao;
import ru.kpfu.itis.group.kadyrov.dao.implementations.GroupDaoImpl;
import ru.kpfu.itis.group.kadyrov.models.Group;
import ru.kpfu.itis.group.kadyrov.services.GroupService;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Амир on 20.11.2016.
 */
public class GroupServiceImpl implements GroupService {
    GroupDao groupDao = new GroupDaoImpl();

    @Override
    public void addGroup(Group group) throws SQLException {
        groupDao.addGroup(group);
    }

    @Override
    public Group findGroupById(int id) {
        return groupDao.findGroupById(id);
    }

    @Override
    public LinkedList<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    public void deleteGroup(int id) {
        groupDao.deleteGroup(id);
    }
}
