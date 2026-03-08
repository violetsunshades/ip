public class Task {
    private final String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    protected String getDescription() {
        return description;
    }

    protected String getTypeIcon() {
        return "[?]";
    }

    protected String getDetails() {
        return "";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + getDetails();
    }
}