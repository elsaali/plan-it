package application;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class AchievementService {
    private static List<String> achievements = new ArrayList<>();

    public static void checkAchievements(Task task) {
        if (task.getPriority().equals("High") && !achievements.contains("High Priority Task Completed")) {
            achievements.add("High Priority Task Completed");
            displayAchievement("Unlocked: High Priority Task Completed!");
        }
    }

    private static void displayAchievement(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setTitle("Achievement Unlocked!");
        alert.showAndWait();
    }

    public static List<String> getAchievements() {
        return achievements;
    }
}

