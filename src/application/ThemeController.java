package application;

import javafx.scene.Scene;

public class ThemeController {

    public static void switchTheme(Scene scene, String theme) {
        scene.getStylesheets().clear(); // Clear existing stylesheets

        if (theme.equalsIgnoreCase("Dark")) {
            scene.getStylesheets().add(ThemeController.class.getResource("/dark-theme.css").toExternalForm());
        } else if (theme.equalsIgnoreCase("Light")) {
            scene.getStylesheets().add(ThemeController.class.getResource("/light-theme.css").toExternalForm());
        }
    }
}
