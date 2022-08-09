package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoObject = new UserDaoJDBCImpl();
        // Создаём таблицу User
        userDaoObject.createUsersTable();

        // Добавляем новых Users
        userDaoObject.saveUser("Олдос", "Хаксли", (byte) 69);
        userDaoObject.saveUser("Рэй", "Брэдбери", (byte) 91);
        userDaoObject.saveUser("Джордж", "Оруэлл", (byte) 46);
        userDaoObject.saveUser("Евгений", "Замятин", (byte) 53);

        // Вывод всех Users
        for (User user : userDaoObject.getAllUsers()) {
            System.out.println(user);
        }

        // Очистка таблицы User
        userDaoObject.cleanUsersTable();

        // Удаление таблицы User
        userDaoObject.dropUsersTable();

    }
}
