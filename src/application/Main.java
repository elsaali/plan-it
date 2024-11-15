package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.getFamilies();
        Font.getFontNames();
        Parent root = FXMLLoader.load(getClass().getResource("/TaskList.fxml"));
        root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setTitle("Plan It!");
        Scene scene = new Scene(root, 500, 600);  // Set consistent size
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);  // Prevent resizing to keep layout consistent
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}