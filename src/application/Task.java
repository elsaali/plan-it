package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class Task implements Comparable<Task> {
    private String name;
    private LocalDate dueDate;
    private Priority priority;
    private boolean completed;

    public Task(String name, LocalDate dueDate, String priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = Priority.fromString(priority);
        this.completed = false; // Default to not completed
    }

    public String getName() {
        return name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, M/d");
        String formattedDate = dueDate.format(formatter);

        return name + " - Due: " + formattedDate + " - Priority: " + priority.name() + (completed ? " (Completed)" : "");
    }

    @Override
    public int compareTo(Task other) {
        int dueDateComparison = this.dueDate.compareTo(other.dueDate);

        if (dueDateComparison != 0) {
            return dueDateComparison;
        }

        return Integer.compare(this.priority.getPriorityValue(), other.priority.getPriorityValue());
    }
}
