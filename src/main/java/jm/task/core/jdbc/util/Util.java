package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public static Connection getConnection() {
        String DB_URL = "";
        String DB_USERNAME = "";
        String DB_PASSWORD = "";

        FileInputStream fis;
        Properties properties = new Properties();

        try {
            // Берём URL БД, имя пользователя и пароль из файла config.properties
            fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
            DB_URL = properties.getProperty("DB_URL");
            DB_USERNAME = properties.getProperty("DB_USERNAME");
            DB_PASSWORD = properties.getProperty("DB_PASSWORD");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        try {
            // Установка соединения
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            connection.setAutoCommit(false);
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return connection;
    }
}
