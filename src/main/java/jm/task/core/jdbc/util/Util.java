package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_URL;
    private static final String DB_USERNAME;
    private static final String DB_PASSWORD;

    static {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("resources/config.properties")) {
            // Берём URL БД, имя пользователя и пароль из файла config.properties
            properties.load(fis);
            DB_URL = properties.getProperty("DB_URL");
            DB_USERNAME = properties.getProperty("DB_USERNAME");
            DB_PASSWORD = properties.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Не удалось найти драйвер!", e);
        }

        try {
            // Установка соединения
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            connection.setAutoCommit(false);
        }
        catch (SQLException throwable) {
            throw new RuntimeException(throwable);
        }
        return connection;
    }
}
