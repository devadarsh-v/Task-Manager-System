package controller;

import model.Task;
import utility.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class TaskManager{

    public void addTask(Task task){
        String sql = "INSERT INTO tasks (title, description, dueDate, isCompleted) VALUES (?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getDueDate().toString());
            pstmt.setBoolean(4, task.isCompleted());
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Task> getTasks(){
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try(Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Task task = new Task(
                        rs.getString("title"),
                        rs.getString("description"),
                        LocalDate.parse(rs.getString("dueDate"))
                        //rs.getBoolean("isCompleted")
                );
                tasks.add(task);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tasks;
    }

    public void updateTask(int id, Task task){
        String sql = "UPDATE tasks SET title = ?, description = ?, dueDate = ?, isCompleted = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getDueDate().toString());
            pstmt.setBoolean(4, task.isCompleted());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteTask(int id){
        String sql = "DELETE FROM tasks WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
