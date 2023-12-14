package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{

    //relative path to the db in the project
    private static final String URL = "jdbc:sqlite:tasks.db";

    /**
     * Connect to the SQLite database
     * @return a Connection object to the database
     */
    public static Connection getConnection(){
        Connection conn = null;
        try{
            // Create a connection to the database
            conn = DriverManager.getConnection(URL);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}

