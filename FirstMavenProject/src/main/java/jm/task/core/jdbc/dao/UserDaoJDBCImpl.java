package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;
    public UserDaoJDBCImpl() {
    }
    static {
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() throws SQLException {
        String create = "CREATE TABLE IF NOT EXISTS `user` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute(create);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

    public void dropUsersTable() throws SQLException {
        String drop = "DROP TABLE IF EXISTS `user`;";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(drop);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String insertNew = "insert into user(name, lastName, age) values(?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertNew);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        System.out.println("Пользователь " + name + " добавлен в таблицу!");

    }

    public void removeUserById(long id) throws SQLException {
        String remove = "delete from user where id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(remove);
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "select * from user";
        List<User> users = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return users;
    }
//    ничего не менял

    public void cleanUsersTable() throws SQLException {
        String clean = "TRUNCATE TABLE user";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(clean);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
