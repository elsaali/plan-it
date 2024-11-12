package application;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static List<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    // Method to get completed tasks
    public static List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) { // Assuming Task class has an isCompleted() method
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }
}
