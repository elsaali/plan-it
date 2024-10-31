package application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskListController {

    @FXML
    private ListView<String> taskListView;

    public void initialize() {
        // Sample data for testing; replace with actual data loading if needed
        ObservableList<String> tasks = FXCollections.observableArrayList(
                "Complete assignment", "Grocery shopping", "Workout session"
        );
        taskListView.setItems(tasks);

        System.out.println("TaskListController initialized with sample tasks");
    }
}