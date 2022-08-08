package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoObj = new UserDaoJDBCImpl();
    public void createUsersTable() {
        userDaoObj.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoObj.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoObj.saveUser(name, lastName, age);
        System.out.printf("User с именем – %s добавлен в базу данных", name);
    }

    public void removeUserById(long id) {
        userDaoObj.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoObj.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoObj.cleanUsersTable();
    }
}
