package view;

import controller.TaskManager;
import model.Task;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView{

    private TaskManager taskManager;
    private Scanner scanner;

    public ConsoleView(TaskManager taskManager){
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu(){
        while(true){
            System.out.println("\nTask Manager");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch(choice){
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addTask(){
        System.out.println("\nAdd New Task");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        LocalDate dueDate = null;
        while(dueDate == null){
            System.out.print("Enter due date (YYYY-MM-DD): ");
            try{
                dueDate = LocalDate.parse(scanner.nextLine());
            }
            catch(DateTimeParseException e){
                System.out.println("Invalid date format. Please try again.");
            }
        }

        Task task = new Task(title, description, dueDate); //deleted false boolean parameter
        taskManager.addTask(task);
        System.out.println("Task added successfully.");
    }

    private void viewTasks(){
        List<Task> tasks = taskManager.getTasks();
        if(tasks.isEmpty()){
            System.out.println("No tasks available.");
        }
        else{
            System.out.println("\nTasks:");
            for(Task task : tasks){
                System.out.println(task);
            }
        }
    }

    private void updateTask(){
        System.out.print("Enter the ID of the task to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new description: ");
        String description = scanner.nextLine();

        LocalDate dueDate = null;
        while(dueDate == null){
            System.out.print("Enter new due date (YYYY-MM-DD): ");
            try{
                dueDate = LocalDate.parse(scanner.nextLine());
            }
            catch(DateTimeParseException e){
                System.out.println("Invalid date format. Please try again.");
            }
        }

        System.out.print("Is the task completed? (true/false): ");
        boolean isCompleted = scanner.nextBoolean();

        Task task = new Task(title, description, dueDate); //deleted the boolean parameter
        taskManager.updateTask(id, task);
        System.out.println("Task updated successfully.");
    }

    private void deleteTask(){
        System.out.print("Enter the ID of the task to delete: ");
        int id = scanner.nextInt();
        taskManager.deleteTask(id);
        System.out.println("Task deleted successfully.");
    }
}
