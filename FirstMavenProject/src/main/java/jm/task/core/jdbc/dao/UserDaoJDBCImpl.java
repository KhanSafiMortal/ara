package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() throws SQLException {
    }

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS `user` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        Statement statement;
        try {
            statement = Util.getMySQLConnection().createStatement();
            statement.execute(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS `user`;";
        Statement statement;
        try {
            statement = Util.getMySQLConnection().createStatement();
            statement.execute(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertNew = "insert into user(name, lastName, age) values(?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Util.getMySQLConnection().prepareStatement(insertNew);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Пользователь " + name + " добавлен в таблицу!");

    }

    public void removeUserById(long id) {
        String remove = "delete from user where id=?";
        try {
            PreparedStatement statement = Util.getMySQLConnection().prepareStatement(remove);
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String query = "select * from user";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = Util.getMySQLConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean = "delete from user";
        Statement statement;
        try {
            statement = Util.getMySQLConnection().createStatement();
            statement.execute(clean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
