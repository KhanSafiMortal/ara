package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        UserDao newUser =  new UserServiceImpl();


        newUser.createUsersTable();
        newUser.saveUser("Oleg", "Mihaylov", (byte) 45);
        newUser.saveUser("Ilya", "Iloyev", (byte) 23);
        newUser.saveUser("Kirill", "Iloyev", (byte) 25);
        newUser.saveUser("Misha", "Olegov", (byte) 29);
        newUser.removeUserById(9);
        newUser.cleanUsersTable();
        newUser.getAllUsers();
        newUser.dropUsersTable();
    }
}
