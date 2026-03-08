public class Deadline extends Task {
    private final String by;

    protected Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    @Override
    protected String getDetails() {
        return " (by: " + by + ")";
    }
}
