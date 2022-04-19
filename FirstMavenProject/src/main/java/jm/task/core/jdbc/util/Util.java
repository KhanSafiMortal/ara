package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getMySQLConnection() throws SQLException {
        String connUrl = "jdbc:mysql://localhost:3306/study";
        String userName = "root";
        String password = "Ser1co2nos";
        return getMySQLConnection(connUrl, userName, password);
    }

    public static Connection getMySQLConnection(String connUrl,
                                                String userName, String password) throws SQLException {


        Connection conn = DriverManager.getConnection(connUrl, userName,
                password);
        return conn;
    }
}
