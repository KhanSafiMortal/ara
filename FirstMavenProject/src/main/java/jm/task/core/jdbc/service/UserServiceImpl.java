package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoJDBC;
//переиспользование Hibernate
    {
        try {
            userDaoJDBC = new UserDaoHibernateImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserServiceImpl() {
    }


    public void createUsersTable() throws SQLException {
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoJDBC.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) throws SQLException {
        userDaoJDBC.removeUserById(id);

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = userDaoJDBC.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        userDaoJDBC.cleanUsersTable();
    }
}
