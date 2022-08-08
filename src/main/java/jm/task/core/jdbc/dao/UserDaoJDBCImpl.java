package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {
        // Пустой конструктор
    }

    public void createUsersTable() {
        // Создание таблицы User
        Statement statement = null;
        executeQuery(QueriesSQL.CREATE_USER_TABLE);
    }

    public void dropUsersTable() {
        // Удаление таблицы User
        executeQuery(QueriesSQL.DROP_USER_TABLE);
    }

    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        // Добавление нового пользователя
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(QueriesSQL.ADD_NEW_USER);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        // Удаление пользователя по id
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(QueriesSQL.REMOVE_USER_BY_ID);
            statement.setLong(1, id);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        // Получение списка всех пользователей из таблицы User
        Statement statement;
        ResultSet data;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            data = statement.executeQuery(QueriesSQL.SELECT_ALL_USERS);
            while (data.next()) {
                users.add(new User(data.getString(2), data.getString(3), data.getByte(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        // Очистка таблицы User
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(QueriesSQL.CLEAN_USERS_TABLE);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
