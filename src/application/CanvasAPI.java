package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CanvasAPI {

    public static void syncAssignments() {
        List<Task> assignments = fetchAssignmentsFromCanvas();
        for (Task assignment : assignments) {
            TaskManager.addTask(assignment);
        }
    }

    // Change from private to public
    public static List<Task> fetchAssignmentsFromCanvas() {
        List<Task> assignments = new ArrayList<>();
        // Placeholder for actual API call logic
        // Add fetched assignments to `assignments` list
        return assignments;
    }
}
