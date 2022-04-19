package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoJDBC;

    {
        try {
            userDaoJDBC = new UserDaoJDBCImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserServiceImpl() {

    }


    public void createUsersTable() {
        userDaoJDBC.createUsersTable();

    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.println("Пользователь " + name + " добавлен в таблицу!");

    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);

    }

    public List<User> getAllUsers() {
        List<User> users = userDaoJDBC.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();

    }
}
