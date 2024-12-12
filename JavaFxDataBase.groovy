import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaFxDataBase{

    public static void main(String[] args) {
        // Database credentials
        String jdbcURL = "jdbc:mysql://localhost:3306/yourdatabase";
        String username = "yourusername";
        String password = "yourpassword";

        Connection connection = null;
        Statement statement = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to the database.");

            // Create a statement
            statement = connection.createStatement();

            // Create a table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "group_number INT)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table created.");

            // Insert data
            String insertDataSQL = "INSERT INTO students (name, group_number) VALUES "
                    + "('Alice', 1), "
                    + "('Bob', 1), "
                    + "('Charlie', 1)";
            statement.executeUpdate(insertDataSQL);
            System.out.println("Data inserted.");

            // Retrieve data
            String selectSQL = "SELECT * FROM students";
            ResultSet resultSet = statement.executeQuery(selectSQL);

            // Display data
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int groupNumber = resultSet.getInt("group_number");
                System.out.println("ID: " + id + ", Name: " + name + ", Group Number: " + groupNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
