package application;

public enum Priority {
    High, Medium, Low;

    public int getPriorityValue() {
        switch (this) {
            case High:   return 3;
            case Medium: return 2;
            case Low:    return 1;
            default:     return 0;
        }
    }

    public static Priority fromString(String priority) {
        switch (priority.toUpperCase()) {
            case "HIGH": return High;
            case "MEDIUM": return Medium;
            case "LOW": return Low;
            default: throw new IllegalArgumentException("Unknown priority: " + priority);
        }
    }

}
