package navis.task;

/**
 * Represents an event task that occurs during a specific time period.
 * An event task contains a description, a start time, and an end time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an event task with the given description and time period.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the type icon for an event task.
     *
     * @return The string "[E]" representing an event task.
     */
    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns additional details about the event, including its time period.
     *
     * @return A formatted string containing the event start and end times.
     */
    @Override
    protected String getDetails() {
        return " (from: " + from + " to: " + to + ")";
    }
}