package application;

import javafx.fxml.FXML;
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

public class TaskListController {

    @FXML
    private ListView<String> taskListView;

    @FXML
    private Label reminderLabel;

    @FXML
    public void initialize() {
        taskListView.getItems().clear();
        boolean taskDueToday = false;

        for (Task task : TaskManager.getTasks()) {
            taskListView.getItems().add(task.toString());
            if (task.getDueDate().equals(LocalDate.now())) {
                taskDueToday = true;
            }
        }

        if (taskDueToday) {
            reminderLabel.setText("You have tasks due today!");
        } else {
            reminderLabel.setText("No reminders for today.");
        }

        taskListView.setOnMouseClicked(this::handleTaskClick);
    }

    private void handleTaskClick(MouseEvent event) {
        String selectedTaskString = taskListView.getSelectionModel().getSelectedItem();

        if (selectedTaskString != null) {
            Task selectedTask = null;
            for (Task task : TaskManager.getTasks()) {
                if (selectedTaskString.equals(task.toString())) {
                    selectedTask = task;
                    break;
                }
            }

            if (selectedTask != null) {
                selectedTask.setCompleted(true);
                TaskManager.getTasks().remove(selectedTask);

                taskListView.getItems().clear();
                for (Task task : TaskManager.getTasks()) {
                    taskListView.getItems().add(task.toString());
                }

                if (TaskManager.getTasks().stream().anyMatch(task -> task.getDueDate().equals(LocalDate.now()))) {
                    reminderLabel.setText("You have tasks due today!");
                } else {
                    reminderLabel.setText("No reminders for today.");
                }
            }
        }
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
