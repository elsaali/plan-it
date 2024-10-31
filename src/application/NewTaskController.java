package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewTaskController {

    @FXML
    private TextField taskNameField;

    @FXML
    private TextField taskDateField;

    @FXML
    private void saveTask() {
        String taskName = taskNameField.getText();
        String taskDate = taskDateField.getText();
        System.out.println("Saving task: " + taskName + " due " + taskDate);
        // Add code here to save the task data
    }
}

