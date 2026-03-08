package navis.task;

/**
 * Represents a todo task without any date or time constraints.
 * A todo task simply contains a description and a completion status.
 */
public class Todo extends Task {

    /**
     * Creates a todo task with the given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon for a todo task.
     *
     * @return The string "[T]" representing a todo task.
     */
    @Override
    protected String getTypeIcon() {
        return "[T]";
    }
}