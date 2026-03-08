package navis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific deadline.
 * A deadline task contains a description and a due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    private final LocalDate by;

    /**
     * Creates a deadline task with the given description and due date.
     *
     * @param description Description of the deadline task.
     * @param by The date by which the task should be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline date of this task.
     *
     * @return The deadline date.
     */
    public LocalDate getBy() {
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
        return " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}