package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        // Создание таблицы User
        userService.createUsersTable();

        // Добавление пользователей
        for (int i = 1; i < 5; i++) {
            userService.saveUser("Name" + i, "Lastname" + i, (byte) (25 + i));
        }

        // Получение всех пользователей и их вывод в консоль
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        // Очистка таблицы User
        userService.cleanUsersTable();

        // Удаление таблицы User
        userService.dropUsersTable();
    }
}
