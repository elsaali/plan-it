package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Main class is the entry point for the     application, "Plan It!".
 * It sets up the primary stage and delegates the layout and functionality to TaskController.
 */
public class Main extends Application {

    /**
     * The start method is called after the application is launched.
     * It initializes the main window of the application.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Set the title of the primary stage (main window)
        primaryStage.setTitle("Plan It!");

        // Instantiate TaskController, which manages the application's main functionality
        TaskController taskController = new TaskController();

        // Retrieve the main layout from TaskController
        VBox mainLayout = taskController.getMainLayout();

        // Create and set the scene with the main layout and specify the window dimensions
        Scene scene = new Scene(mainLayout, 800, 800);
        primaryStage.setScene(scene);

        // Display the primary stage
        primaryStage.show();
    }

    /**
     * The main method launches the application.
     *
     * @param args Command-line arguments (if any).
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
