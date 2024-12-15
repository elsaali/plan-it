package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The TaskController class manages the core functionality of the "Plan It!" application.
 * It handles task input, displays tasks in a table view, and organizes tasks using an ArrayList
 * and a PriorityQueue to sort by priority.
 */
public class TaskController {

    // Internal data structures for task management
    private final ArrayList<Task> taskArrayList = new ArrayList<>();
    private final PriorityQueue<Task> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Task::getPriorityLevel));
    private final ObservableList<Task> taskList = FXCollections.observableArrayList();
    private final ObservableList<Task> completedTasks = FXCollections.observableArrayList();

    // UI components
    private final TableView<Task> taskTableView = new TableView<>();
    private final TableView<Task> completedTaskTableView = new TableView<>();
    private final VBox mainLayout = new VBox(10);

    /**
     * Constructor for TaskController. Sets up the UI components and defines behavior for
     * adding, deleting, and managing tasks.
     */
    public TaskController() {
        mainLayout.setPadding(new Insets(10));

        // Task Input Fields
        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Task Name");

        DatePicker deadlinePicker = new DatePicker();
        deadlinePicker.setPromptText("Pick Deadline");

        ComboBox<String> priorityBox = new ComboBox<>();
        priorityBox.getItems().addAll("High", "Medium", "Low");
        priorityBox.setPromptText("Priority");

        TextArea notesField = new TextArea();
        notesField.setPromptText("Enter Notes...");
        notesField.setPrefRowCount(3); // Allow multiline input

        // Buttons for task operations
        Button addButton = new Button("Add Task");
        Button syncButton = new Button("Sync with Canvas");
        Button googleButton = new Button("Sync with Google Calendar");

        // Input layouts
        HBox inputLayout1 = new HBox(10, taskNameField, deadlinePicker, priorityBox);
        HBox inputLayout2 = new HBox(10, notesField, addButton);
        HBox inputLayout3 = new HBox(10, syncButton, googleButton);

        // Setup task table columns
        TableColumn<Task, Boolean> taskCheckBoxColumn = new TableColumn<>("Complete");
        taskCheckBoxColumn.setCellValueFactory(cellData -> cellData.getValue().completedProperty());
        taskCheckBoxColumn.setCellFactory(col -> new CheckBoxTableCell<>());
        taskCheckBoxColumn.setEditable(true);

        TableColumn<Task, String> taskNameColumn = new TableColumn<>("Task Name");
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        taskNameColumn.setPrefWidth(200);

        TableColumn<Task, String> taskDeadlineColumn = new TableColumn<>("Deadline");
        taskDeadlineColumn.setCellValueFactory(cellData -> cellData.getValue().deadlineProperty());

        TableColumn<Task, String> taskPriorityColumn = new TableColumn<>("Priority");
        taskPriorityColumn.setCellValueFactory(cellData -> cellData.getValue().priorityProperty());

        TableColumn<Task, String> taskNotesColumn = new TableColumn<>("Notes");
        taskNotesColumn.setCellValueFactory(cellData -> cellData.getValue().notesProperty());
        taskNotesColumn.setPrefWidth(250);

        // Delete column for to-do list
        TableColumn<Task, Void> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellFactory(col -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Task task = getTableRow().getItem();
                    if (task != null) {
                        taskList.remove(task);
                        taskArrayList.remove(task);
                        priorityQueue.remove(task);
                        completedTasks.remove(task);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setGraphic(deleteButton);
                } else {
                    setGraphic(null);
                }
            }
        });

        taskTableView.getColumns().addAll(taskCheckBoxColumn, taskNameColumn, taskDeadlineColumn, taskPriorityColumn, taskNotesColumn, deleteColumn);
        taskTableView.setItems(taskList);
        taskTableView.setEditable(true);

        // Setup completed tasks table columns
        TableColumn<Task, String> completedTaskNameColumn = new TableColumn<>("Task Name");
        completedTaskNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        completedTaskNameColumn.setPrefWidth(260);

        TableColumn<Task, String> completedTaskDeadlineColumn = new TableColumn<>("Deadline");
        completedTaskDeadlineColumn.setCellValueFactory(cellData -> cellData.getValue().deadlineProperty());

        TableColumn<Task, String> completedTaskPriorityColumn = new TableColumn<>("Priority");
        completedTaskPriorityColumn.setCellValueFactory(cellData -> cellData.getValue().priorityProperty());

        TableColumn<Task, String> completedTaskNotesColumn = new TableColumn<>("Notes");
        completedTaskNotesColumn.setCellValueFactory(cellData -> cellData.getValue().notesProperty());
        completedTaskNotesColumn.setPrefWidth(270);

        // Delete column for completed tasks
        TableColumn<Task, Void> completedDeleteColumn = new TableColumn<>("Delete");
        completedDeleteColumn.setCellFactory(col -> new TableCell<>() {
            private final Button completedDeleteButton = new Button("Delete");

            {
                completedDeleteButton.setOnAction(event -> {
                    Task task = getTableRow().getItem();
                    if (task != null) {
                        completedTasks.remove(task);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setGraphic(completedDeleteButton);
                } else {
                    setGraphic(null);
                }
            }
        });

        completedTaskTableView.getColumns().addAll(completedTaskNameColumn, completedTaskDeadlineColumn, completedTaskPriorityColumn, completedTaskNotesColumn, completedDeleteColumn);
        completedTaskTableView.setItems(completedTasks);

        // Setup the main layout
        TitledPane toDoPane = new TitledPane("To-Do List", taskTableView);
        TitledPane completedPane = new TitledPane("Completed Tasks", completedTaskTableView);
        mainLayout.getChildren().addAll(inputLayout1, inputLayout2, toDoPane, completedPane, inputLayout3);

        // Add Task Action
        addButton.setOnAction(e -> {
            String taskName = taskNameField.getText();
            var deadline = deadlinePicker.getValue();
            String priority = priorityBox.getValue();
            String notes = notesField.getText();

            if (taskName.isEmpty() || deadline == null || priority == null) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            Task newTask = new Task(taskName, deadline.toString(), priority, notes);
            taskArrayList.add(newTask);
            priorityQueue.offer(newTask);
            refreshTaskList();

            taskNameField.clear();
            deadlinePicker.setValue(null);
            priorityBox.setValue(null);
            notesField.clear();
        });

        // Sync Buttons Actions
        syncButton.setOnAction(e -> syncWithCanvas());
        googleButton.setOnAction(e -> syncWithCalendar());
    }

    /**
     * Returns the main layout for this controller.
     *
     * @return The main VBox layout.
     */
    public VBox getMainLayout() {
        return mainLayout;
    }

    /**
     * Refreshes the to-do list by updating the observable list from the priority queue.
     */
    private void refreshTaskList() {
        taskList.setAll(priorityQueue);
    }

    /**
     * Displays an alert dialog with the specified title and content.
     *
     * @param title   The title of the alert dialog.
     * @param content The content of the alert dialog.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Simulates syncing tasks with Canvas API.
     */
    private void syncWithCanvas() {
        taskList.addAll(
                new Task("Project Codes", "2024-12-15", "High", ""),
                new Task("Project Presentation", "2024-12-15", "Medium", ""),
                new Task("Final Exam", "2024-12-14", "High", "")
        );
        showAlert("Success", "Tasks synced with Canvas!");
    }

    /**
     * Simulates syncing tasks with Google Calendar API.
     */
    private void syncWithCalendar() {
        // Simulated API call
        taskList.addAll(
                new Task("Project Codes", "2024-12-15", "High", "Complete the final review and submit."),
                new Task("Team Meeting", "2024-12-16", "Medium", "Discuss project milestones and next steps."),
                new Task("Bug Fixing", "2024-12-17", "High", "Resolve critical issues in the codebase."),
                new Task("Documentation", "2024-12-18", "Low", "Update the user guide and technical documentation."),
                new Task("Client Presentation", "2024-12-19", "High", "Prepare slides and rehearse the presentation."),
                new Task("Code Optimization", "2024-12-20", "Medium", "Refactor and optimize performance-critical sections."),
                new Task("Feedback Review", "2024-12-21", "Low", "Analyze and incorporate feedback from the client.")
        );
        showAlert("Success", "Tasks synced with Google Calendar!");
    }

    /**
     * A custom TableCell class with a CheckBox for task completion.
     *
     * @param <S> The type of the TableView generic type.
     */
    class CheckBoxTableCell<S> extends TableCell<S, Boolean> {
        private final CheckBox checkBox;

        public CheckBoxTableCell() {
            this.checkBox = new CheckBox();
            this.checkBox.setOnAction(event -> {
                Task task = (Task) getTableRow().getItem();
                if (task != null) {
                    task.setCompleted(checkBox.isSelected());
                    if (checkBox.isSelected()) {
                        completedTasks.add(task);
                        taskList.remove(task);
                        taskArrayList.remove(task);
                        priorityQueue.remove(task);
                    }
                }
            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic(checkBox);
                checkBox.setSelected(item != null && item);
            } else {
                setGraphic(null);
            }
        }
    }
}
