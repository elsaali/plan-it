package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));
        primaryStage.setTitle("Plan It!");
        Scene scene = new Scene(root, 800, 600);  // Set consistent size
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);  // Prevent resizing to keep layout consistent
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
