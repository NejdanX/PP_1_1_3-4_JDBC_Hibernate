package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl userDaoHibernateObj = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userDaoHibernateObj.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernateObj.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernateObj.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHibernateObj.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoHibernateObj.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoHibernateObj.cleanUsersTable();
    }
}
