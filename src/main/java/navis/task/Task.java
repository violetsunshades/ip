package navis.task;

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

    public boolean isDone() { return isDone; }

    public String getDescription() {
        return description;
    }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
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