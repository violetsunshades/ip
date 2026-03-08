package navis.task;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() { return by; }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    @Override
    protected String getDetails() {
        return " (by: " + by + ")";
    }
}