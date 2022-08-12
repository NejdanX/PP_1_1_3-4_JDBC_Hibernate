package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        // Создание таблицы User
        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS User (" +
                    "id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(25), " +
                    "last_name VARCHAR(25), " +
                    "age TINYINT UNSIGNED" +
                ")";
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.createSQLQuery(CREATE_USER_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Произошла ошибка при создании таблицы User. Изменения отменены.");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        // Удаление таблицы User
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS User";
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.createSQLQuery(DROP_USER_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Произошла ошибка при удалении таблицы User. Изменения отменены.");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        // Добавление нового пользователя
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.printf("%s добавлен в базу данных\n", user);
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("При попытке добавления пользователя произошла ошибка. Изменения отменены.");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        // Удаление пользователя по id
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.printf("User с id=%d удалён из базы данных\n", id);
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("При попытке удаления пользователя произошла ошибка. Изменения отменены.");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        // Получение списка всех пользователей из таблицы User
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            users = session.createQuery("FROM User").list();
        }
        catch (Exception e) {
            System.out.println("При попытке получения списка пользователей произошла ошибка. Изменения отменены.");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        // Очистка таблицы User
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Произошла ошибка при очистки таблицы User. Изменения отменены.");
            e.printStackTrace();
        }
    }
}
