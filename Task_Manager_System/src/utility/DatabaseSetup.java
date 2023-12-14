package utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup{
    private static final String CREATE_TASKS_TABLE = "CREATE TABLE IF NOT EXISTS tasks ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "title TEXT NOT NULL, "
            + "description TEXT, "
            + "dueDate TEXT, " // ("YYYY-MM-DD HH:MM:SS.SSS")
            + "isCompleted BOOLEAN NOT NULL DEFAULT 0" // Use 0 (false) and 1 (true)
            + ");";

    // initialize database with the required tables
    public static void initializeDatabase(){
        try(Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement()){
            // Create tasks table if it doesn't exist.
            stmt.execute(CREATE_TASKS_TABLE);
            System.out.println("Initialization complete. Tasks table is ready to be used.");
        }
        catch(SQLException e){
            throw new RuntimeException("Database initialization failed", e);
        }
    }

    //main method to run database
    public static void main(String[] args){
        initializeDatabase();
    }
}
