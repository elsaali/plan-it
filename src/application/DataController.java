package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataController {
    private static final String CSV_FILE = "tasks.csv";

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        // Logic to read from tasks.csv and populate the list
        return tasks;
    }

    public void saveTask(Task task) {
        // Logic to append a new task to tasks.csv
    }
}