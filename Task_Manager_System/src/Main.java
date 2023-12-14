import controller.TaskManager;
import view.ConsoleView;
import utility.DatabaseSetup;

public class Main{
    public static void main(String[] args){
        DatabaseSetup.initializeDatabase();
        TaskManager taskManager = new TaskManager();
        ConsoleView view = new ConsoleView(taskManager);
        view.showMenu();
    }
}
