package jm.task.core.jdbc.util;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;


public class Util {
    public static Connection getMySQLConnection() throws SQLException {
        String connUrl = "jdbc:mysql://localhost:3306/study";
        String userName = "root";
        String password = "Ser1co2nos";
        return getMySQLConnection(connUrl, userName, password);
    }

    public static Connection getMySQLConnection(String connUrl,
                                                String userName, String password) throws SQLException {


        return DriverManager.getConnection(connUrl, userName, password);
    }


    private static SessionFactory sessionFactory;
// вроде все по инструкции делал

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/study");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Ser1co2nos");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
