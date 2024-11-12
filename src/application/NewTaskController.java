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
        LocalDate dueDate = taskDueDateField.getValue();
        String priority = priorityComboBox.getValue();

        if (taskName.isEmpty() || dueDate == null || priority == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        // Create new task with priority
        Task task = new Task(taskName, dueDate, priority);
        TaskManager.addTask(task);

        // Check for achievements after adding the task
        AchievementService.checkAchievements(task);

        // Confirmation alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Task saved successfully.");
        alert.showAndWait();

        // Clear input fields after saving
        taskNameField.clear();
        taskDueDateField.setValue(null);
        priorityComboBox.getSelectionModel().clearSelection();

        // Navigate back to Dashboard after saving
        goBack(event);
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            System.out.println("Navigating back to Dashboard...");
            Parent root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Dashboard.fxml");
            e.printStackTrace();
        }
    }
}
