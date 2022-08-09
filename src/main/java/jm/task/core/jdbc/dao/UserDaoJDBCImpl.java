package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();

    // SQL-запросы
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
    
    public UserDaoJDBCImpl() {
        // Пустой конструктор
    }

    public void createUsersTable() {
        // Создание таблицы User
        try {
            executeQuery(CREATE_USER_TABLE);
        }
        catch (SQLException e) {
            System.out.println("Произошла ошибка при создании таблицы User. Изменения отменены.");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        // Удаление таблицы User
        try {
            executeQuery(DROP_USER_TABLE);
        }
        catch (SQLException e) {
            System.out.println("Произошла ошибка при удалении таблицы User. Изменения отменены.");
            e.printStackTrace();
        }
    }

    public void executeQuery(String query) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.commit();
        } catch (SQLException e) {
            // Откатываем изменения
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        // Добавление нового пользователя
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(ADD_NEW_USER);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            }
            catch (SQLException ignore) {
                System.out.println("При попытке добавления пользователя произошла ошибка. Изменения отменены.");
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        // Удаление пользователя по id
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(REMOVE_USER_BY_ID);
            statement.setLong(1, id);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            }
            catch (SQLException ignore) {
                System.out.println("При попытке удаления пользователя произошла ошибка. Изменения отменены.");
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        // Получение списка всех пользователей из таблицы User
        Statement statement;
        ResultSet data;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            data = statement.executeQuery(SELECT_ALL_USERS);
            while (data.next()) {
                users.add(new User(data.getString("name"), data.getString("last_name"), data.getByte("age")));
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            }
            catch (SQLException ignore) {
                System.out.println("При попытке получения списка пользователей произошла ошибка. Изменения отменены.");
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        // Очистка таблицы User
        try {
            executeQuery(CLEAN_USERS_TABLE);
        }
        catch (SQLException e) {
            System.out.println("Произошла ошибка при очистки таблицы User. Изменения отменены.");
            e.printStackTrace();
        }
    }
}
