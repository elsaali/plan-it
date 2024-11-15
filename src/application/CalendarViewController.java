package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CalendarViewController {

    @FXML
    private ListView<String> taskListView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private GridPane calendarGrid;

    @FXML
    public void initialize() {
        updateTaskList();
        updateProgress();
        populateCalendar();
    }

    private void updateTaskList() {
        taskListView.getItems().clear();
        for (Task task : TaskManager.getTasks()) {
            taskListView.getItems().add(task.toString());
        }
    }

    private void updateProgress() {
        int completedTasks = TaskManager.getCompletedTasks().size();
        int totalTasks = TaskManager.getTasks().size();
        double progress = totalTasks == 0 ? 0 : (double) completedTasks / totalTasks;
        progressBar.setProgress(progress);
    }

    private void populateCalendar() {
        LocalDate today = LocalDate.now();
        int daysInMonth = today.lengthOfMonth();

        calendarGrid.getChildren().clear();

        for (int day = 1; day <= daysInMonth; day++) {
            Label dayLabel = new Label(String.valueOf(day));
            VBox dayBox = new VBox(dayLabel);

            LocalDate currentDate = today.withDayOfMonth(day);
            for (Task task : TaskManager.getTasks()) {
                if (task.getDueDate().equals(currentDate)) {
                    Label taskLabel = new Label(task.getName());
                    dayBox.getChildren().add(taskLabel);
                }
            }
            calendarGrid.add(dayBox, (day - 1) % 7, (day - 1) / 7); // Arrange in grid layout
        }
    }

    @FXML
    private void openNewTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/NewTask.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 500, 600));
            stage.setMaximized(true);  // Full-screen mode
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
