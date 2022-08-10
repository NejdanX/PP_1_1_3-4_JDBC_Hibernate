package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS User (" +
            "id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(25), " +
            "last_name VARCHAR(25), " +
            "age TINYINT UNSIGNED)";

    String DROP_USER_TABLE = "DROP TABLE IF EXISTS User";
    String ADD_NEW_USER = "INSERT INTO user(name, last_name, age) VALUES (?, ?, ?)";
    String REMOVE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    String SELECT_ALL_USERS = "SELECT * FROM user";
    String CLEAN_USERS_TABLE = "DELETE FROM user";
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
