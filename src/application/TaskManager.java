package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager {
    private static List<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
        sortTasks();
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static void sortTasks() {
        Collections.sort(tasks, (task1, task2) -> {
            int dateComparison = task1.getDueDate().compareTo(task2.getDueDate());

            if (dateComparison == 0) {
                return Integer.compare(task2.getPriority().getPriorityValue(), task1.getPriority().getPriorityValue());
            }

            return dateComparison;
        });
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
