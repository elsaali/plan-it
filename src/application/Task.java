package application;

import java.time.LocalDate;

public class Task {
    private String name;
    private LocalDate dueDate;
    private String priority;
    private boolean completed;

    public Task(String name, LocalDate dueDate, String priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false; // Default to not completed
    }

    public String getName() {
        return name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return name + " - Due: " + dueDate + " - Priority: " + priority + (completed ? " (Completed)" : "");
    }
}
