package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    public void saveTask(ActionEvent event) {
        String taskName = taskNameField.getText();
        LocalDate dueDate = taskDueDateField.getValue();

        if (taskName.isEmpty() || dueDate == null) {
            Alert alert = new Alert(AlertType.ERROR, "Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        Task task = new Task(taskName, dueDate);
        TaskManager.addTask(task);

        Alert alert = new Alert(AlertType.INFORMATION, "Task saved successfully.");
        alert.showAndWait();

        taskNameField.clear();
        taskDueDateField.setValue(null);

        goBack(event);  // Navigate back after saving
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

