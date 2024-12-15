package application;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a Task with attributes such as name, deadline, priority, and notes.
 * Each task also has a "completed" status which can be toggled.
 */
public class Task {

    // The name of the task
    private final String name;

    // The deadline for the task
    private final String deadline;

    // The priority of the task (e.g., High, Medium, Low)
    private final String priority;

    // Additional notes about the task
    private final String notes;

    // A BooleanProperty to track whether the task is completed
    private final BooleanProperty completed;

    /**
     * Constructs a new Task object with the specified name, deadline, priority, and notes.
     *
     * @param name     the name of the task
     * @param deadline the deadline of the task
     * @param priority the priority level of the task
     * @param notes    any additional notes about the task
     */
    public Task(String name, String deadline, String priority, String notes) {
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.notes = notes;
        this.completed = new SimpleBooleanProperty(false); // Default to not completed
    }

    // Getters

    /**
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * @return the deadline of the task
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * @return the priority level of the task
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @return the notes associated with the task
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Converts the priority level to an integer value for comparison purposes.
     *
     * @return an integer representing the priority level (1 for High, 2 for Medium, 3 for Low)
     */
    public int getPriorityLevel() {
        return switch (priority) {
            case "High" -> 1;
            case "Medium" -> 2;
            default -> 3; // Low
        };
    }

    /**
     * @return the BooleanProperty representing the completion status of the task
     */
    public BooleanProperty completedProperty() {
        return completed;
    }

    /**
     * @return true if the task is marked as completed, false otherwise
     */
    public boolean isCompleted() {
        return completed.get();
    }

    /**
     * Sets the completion status of the task.
     *
     * @param completed true to mark the task as completed, false to mark it as incomplete
     */
    public void setCompleted(boolean completed) {
        this.completed.set(completed);
    }

    // JavaFX Property Bindings for TableView compatibility

    /**
     * @return a StringProperty representing the name of the task
     */
    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    /**
     * @return a StringProperty representing the deadline of the task
     */
    public StringProperty deadlineProperty() {
        return new SimpleStringProperty(deadline);
    }

    /**
     * @return a StringProperty representing the priority level of the task
     */
    public StringProperty priorityProperty() {
        return new SimpleStringProperty(priority);
    }

    /**
     * @return a StringProperty representing the notes of the task
     */
    public StringProperty notesProperty() {
        return new SimpleStringProperty(notes);
    }

    /**
     * Overrides the default toString method to provide a meaningful representation of the task.
     *
     * @return a string containing the name, deadline, priority, and notes of the task
     */
    @Override
    public String toString() {
        return name + " (Due: " + deadline + ", Priority: " + priority + ", Notes: " + notes + ")";
    }
}
