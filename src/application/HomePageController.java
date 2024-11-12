package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class HomePageController {

    @FXML
    private void openNewTask(ActionEvent event) {
        loadScene(event, "/NewTask.fxml");
    }

    @FXML
    private void showTaskList(ActionEvent event) {
        loadScene(event, "/TaskList.fxml");
    }

    @FXML
    private void syncWithCanvas(ActionEvent event) {
        CanvasAPI.syncAssignments();  // Ensure this calls syncAssignments
        // Update the UI or task list as needed here
    }

    private void loadScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

