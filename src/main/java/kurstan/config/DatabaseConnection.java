package kurstan.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author kurstan
 * @created at 22.01.2023 15:46
 */
public class DatabaseConnection {

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres"
            );
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
