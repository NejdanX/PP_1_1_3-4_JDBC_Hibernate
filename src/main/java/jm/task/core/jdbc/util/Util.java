package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String DB_URL;
    private static final String DB_USERNAME;
    private static final String DB_PASSWORD;

    private static final Properties settings = new Properties();

    private static SessionFactory sessionFactory;

    static {
        try (FileInputStream fis = new FileInputStream("resources/config.properties")) {
            // Берём URL БД, имя пользователя и пароль из файла config.properties
            settings.load(fis);
            DB_URL = settings.getProperty("DB_URL");
            DB_USERNAME = settings.getProperty("DB_USERNAME");
            DB_PASSWORD = settings.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName(settings.getProperty("DRIVER"));
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

    public static void tuneHibernate() {
        Configuration config = new Configuration();
        config.setProperty(Environment.DRIVER, settings.getProperty("DRIVER"));
        config.setProperty(Environment.URL, settings.getProperty("DB_URL"));
        config.setProperty(Environment.USER, settings.getProperty("DB_USERNAME"));
        config.setProperty(Environment.PASS, settings.getProperty("DB_PASSWORD"));
        config.setProperty(Environment.DIALECT, settings.getProperty("DIALECT"));
        config.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        tuneHibernate();
        return sessionFactory;
    }
}
