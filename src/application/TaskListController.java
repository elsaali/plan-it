package application;


import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;



public class TaskListController {


    @FXML
    private ListView<String> taskListView;


    @FXML
    private Label reminderLabel;


    @FXML
    public void initialize() {
        updateTaskList();
        adjustListViewHeight();
        setTaskListCellFactory();
    }

    private void setTaskListCellFactory() {
        // Set a custom cell factory for the ListView to control the height of each item
        taskListView.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #E4B1F0;");
                }

                // Set a fixed height for each item in the ListView
                setPrefHeight(25);  // Fixed height for each task item (in pixels)
            }
        });
    }


    private void updateTaskList() {
        taskListView.getItems().clear();  // Clear the existing items


        for (Task task : TaskManager.getTasks()) {
            taskListView.getItems().add(task.toString());
        }


        // Update reminder label based on tasks due today
        boolean taskDueToday = false;
        for (Task task : TaskManager.getTasks()) {
            if (task.getDueDate().equals(LocalDate.now())) {
                taskDueToday = true;
            }
        }


        if (taskDueToday) {
            reminderLabel.setText("You have tasks due today!");
        } else {
            reminderLabel.setText("No reminders for today.");
        }


        // Dynamically adjust the ListView height based on the number of tasks
        adjustListViewHeight();


        // Handle task clicks
        taskListView.setOnMouseClicked(event -> handleTaskClick(event));
    }


    private void adjustListViewHeight() {
        int taskCount = TaskManager.getTasks().size();

        // Set the fixed height for each task item (adjust based on your actual item height)
        double itemHeight = 25;  // Height for each item (in pixels), adjust this based on your list item height

        // Calculate the total height based on the number of tasks
        double totalHeight = taskCount * itemHeight + 2;


        // Apply the calculated height to the ListView
        taskListView.setPrefHeight(totalHeight);
    }


    private void handleTaskClick(MouseEvent event) {
        String selectedTaskString = taskListView.getSelectionModel().getSelectedItem();


        if (selectedTaskString != null) {
            Task selectedTask = null;


            // Find the task object from TaskManager using the selected task string
            for (Task task : TaskManager.getTasks()) {
                if (selectedTaskString.equals(task.toString())) {
                    selectedTask = task;
                    break;
                }
            }


            if (selectedTask != null) {
                // Mark the task as completed and remove it from the TaskManager
                selectedTask.setCompleted(true);
                TaskManager.getTasks().remove(selectedTask);


                // Update the ListView to reflect the removed task
                updateTaskList();
            }
        }
    }

    @FXML
    private void openNewTask(ActionEvent event) {
        loadScene(event, "/NewTask.fxml");
    }


    @FXML
    private void syncWithCanvas(ActionEvent event) {
        CanvasAPI.syncAssignments();  // Ensure this calls syncAssignments
        showInfoAlert("Canvas Sync", "Syncing with Canvas is not implemented yet.");
    }


    private void loadScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showInfoAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
