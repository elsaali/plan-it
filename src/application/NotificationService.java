package application;

import javafx.scene.control.Alert;
import java.time.LocalDate;

public class NotificationService {

    public static void checkForDueTasks() {
        for (Task task : TaskManager.getTasks()) {
            if (task.getDueDate().equals(LocalDate.now())) {
                showReminder(task);
            }
        }
    }

    private static void showReminder(Task task) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Task Reminder");
        alert.setHeaderText(null);
        alert.setContentText("Reminder: Task '" + task.getName() + "' is due today!");
        alert.showAndWait();
    }
}