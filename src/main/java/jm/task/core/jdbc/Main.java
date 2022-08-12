package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

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
