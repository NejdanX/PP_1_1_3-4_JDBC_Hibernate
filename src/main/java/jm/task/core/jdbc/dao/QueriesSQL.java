package jm.task.core.jdbc.dao;

public class QueriesSQL {
    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS User ( " +
            "id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(25), " +
            "last_name VARCHAR(25), " +
            "age TINYINT UNSIGNED )";

    static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS User";
    static final String ADD_NEW_USER = "INSERT INTO user(name, last_name, age) VALUES (?, ?, ?)";
    static final String REMOVE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    static final String SELECT_ALL_USERS = "SELECT * FROM user";
    static final String CLEAN_USERS_TABLE = "DELETE FROM user";

}
