package navis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    @Override
    protected String getDetails() {
        return " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}