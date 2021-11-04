package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() {
          String dbURL = "jdbc:mysql://localhost:3306/user_table";
          String dbUsername = "root";
          String dbPassword = "root";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться: " + e.getMessage());
        }

        return connection;
    }

}
