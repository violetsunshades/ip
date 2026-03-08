package navis.task;

/**
 * Represents a task that must be completed by a specific deadline.
 * A deadline task contains a description and a due time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Creates a deadline task with the given description and due time.
     *
     * @param description Description of the deadline task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of this task.
     *
     * @return The deadline string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the type icon for a deadline task.
     *
     * @return The string "[D]" representing a deadline task.
     */
    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns additional details about the deadline.
     *
     * @return A formatted string containing the deadline.
     */
    @Override
    protected String getDetails() {
        return " (by: " + by + ")";
    }
}