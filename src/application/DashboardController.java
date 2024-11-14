package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import javafx.stage.Screen;

public class DashboardController {

    @FXML
    private ListView<String> taskListView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private PieChart taskProgressChart;
    @FXML
    private VBox root; // Ensure fx:id="root" is set in your VBox in the FXML file

    @FXML
    public void initialize() {
        updateTaskList();
        updateProgressVisualization();
        System.out.println("Dashboard initialized.");
    }

    private void updateTaskList() {
        taskListView.getItems().clear();
        taskListView.getItems().add("Task 1 - Due today");
        taskListView.getItems().add("Task 2 - Due tomorrow");
        taskListView.getItems().add("Task 3 - Overdue");
    }

    private void updateProgressVisualization() {
        int completedTasks = 3;
        int remainingTasks = 7;

        taskProgressChart.getData().clear();
        taskProgressChart.getData().add(new PieChart.Data("Completed", completedTasks));
        taskProgressChart.getData().add(new PieChart.Data("Remaining", remainingTasks));

        int totalTasks = completedTasks + remainingTasks;
        double progress = totalTasks == 0 ? 0 : (double) completedTasks / totalTasks;
        progressBar.setProgress(progress);
    }

    @FXML
    private void syncWithCanvas(ActionEvent event) {
        System.out.println("Sync with Canvas clicked.");
        showInfoAlert("Canvas Sync", "Syncing with Canvas is not implemented yet.");
    }

    @FXML
    private void openNewTask(ActionEvent event) {
        try {
            // Load the new task scene
            Parent root = FXMLLoader.load(getClass().getResource("/NewTask.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set a fixed size for the window
            stage.setWidth(800);  // Set fixed width
            stage.setHeight(600); // Set fixed height

            // Show the new scene
            stage.setScene(new Scene(root, 800, 600));  // Ensure the new scene has a fixed size too
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBackToHome(ActionEvent event) {
        try {
            // Load the HomePage.fxml file
            Parent root = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));

            // Get the current stage (window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the new root (HomePage.fxml)
            Scene scene = new Scene(root);

            // Set a fixed size for the window (you can adjust the size here as needed)
            stage.setScene(scene);

            // Optionally, set the window size explicitly
            stage.setWidth(800);   // Set width of the window
            stage.setHeight(600);  // Set height of the window

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void applyDarkTheme(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/resources/dark-theme.css").toExternalForm());
        System.out.println("Dark theme applied.");
    }

    @FXML
    private void applyLightTheme(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/resources/light-theme.css").toExternalForm());
        System.out.println("Light theme applied.");
    }

    private void loadScene(ActionEvent event, String fxmlFile, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            // Set scene to the size of the primary screen dimensions
            stage.setScene(scene);
            stage.setTitle(title);

            // Get primary screen dimensions and set the window size
            stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
            stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());

            stage.centerOnScreen(); // Center the window on the screen
            stage.show();

            System.out.println("Navigated to: " + title);
        } catch (IOException e) {
            e.printStackTrace();
            showInfoAlert("Error", "Could not load the page: " + title);
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
