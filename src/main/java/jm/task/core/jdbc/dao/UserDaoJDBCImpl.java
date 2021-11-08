package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement;

        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("CREATE TABLE users_table" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(80), lastName VARCHAR(100), age TINYINT);"
            );
            preparedStatement.executeUpdate();

            connection.commit();

            System.out.println("Table created in given database.");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement;

        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS users_table;");
            preparedStatement.executeUpdate();

            connection.commit();

            System.out.println("Table deleted in given database.");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement;

        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users_table(name, lastName, age) VALUES(?, ?, ?);"
            );

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

            connection.commit();

            System.out.println("User с именем " + name + " добавлен в базу данных.");

        } catch (SQLException e) {
            System.out.println("Exception while saving a user: " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement;

        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("DELETE FROM users_table WHERE id = ?;");

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            connection.commit();

            System.out.println("User with ID " + id + " was deleted from database.");

        } catch (SQLException e) {
            System.out.println("Exception while saving a user: " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement;

        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("SELECT id, name, lastName, age FROM users_table;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Byte age = resultSet.getByte(4);

                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);

                usersList.add(user);
            }

            connection.commit();

        } catch (SQLException e) {
            System.out.println("Exception while getting users data: " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        }

        return usersList;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement;

        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("TRUNCATE TABLE users_table;");
            preparedStatement.executeUpdate();

            connection.commit();

            System.out.println("All rows in table was deleted.");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        }
    }
}
