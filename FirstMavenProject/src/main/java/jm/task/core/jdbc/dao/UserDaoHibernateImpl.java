package jm.task.core.jdbc.dao;


import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl(){


    }
    // реализация Hibernate
    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS `user` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        Transaction transaction = null;
        try {
            Session session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery(create);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS user;";
        Transaction transaction = null;
        try {
            Session session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(drop);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        Session session;
        try {
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session;

        try {
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<User> getAllUsers() {
        Transaction transaction = null;
        Session session;
        List<User> users = null;
        try {
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            users = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session;
        String clean = "TRUNCATE TABLE user;";
        try {
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.createQuery(clean);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}