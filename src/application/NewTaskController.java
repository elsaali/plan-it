package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;
import java.time.LocalDate;

public class NewTaskController {

    @FXML
    private TextField taskNameField;

    @FXML
    private DatePicker taskDueDateField;

    @FXML
    private ComboBox<String> priorityComboBox;

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {
        // Populate the priority ComboBox with options
        priorityComboBox.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));

        // Style and setup for an enhanced UI experience
        saveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        cancelButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
    }

    @FXML
    public void saveTask(ActionEvent event) {
        String taskName = taskNameField.getText();
        LocalDate taskDueDate = taskDueDateField.getValue();
        String taskPriority = priorityComboBox.getValue();

        // Validate the input
        if (taskName != null && taskDueDate != null && taskPriority != null) {
            // Create a new Task object
            Task newTask = new Task(taskName, taskDueDate, taskPriority);
            // Add the task to the TaskManager (static list of tasks)
            TaskManager.addTask(newTask);

            // Navigate back to the HomePage
            navigateToTaskList(event);
        } else {
            // Show an error alert if the fields are incomplete
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        }
    }

    private void navigateToTaskList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TaskList.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 500, 600);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load the TaskList.");
            alert.showAndWait();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/TaskList.fxml"));

            Scene scene = new Scene(root, 500, 600);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);  // Ensure fixed size for the scene
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
