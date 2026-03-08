package navis.task;

/**
 * Represents a generic task in the Navis application.
 * A task has a description and a completion status.
 * Specific task types such as Todo, Deadline, and Event extend this class.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description Description of the task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion status of this task.
     *
     * @param isDone True if the task is completed, false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns whether the task has been completed.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     * "[X]" indicates the task is completed, "[ ]" indicates it is not completed.
     *
     * @return The status icon string.
     */
    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the type icon of the task.
     * Subclasses override this method to provide their own type icons.
     *
     * @return The type icon string.
     */
    protected String getTypeIcon() {
        return "[?]";
    }

    /**
     * Returns additional details about the task.
     * Subclasses override this method to provide extra information
     * such as deadlines or event times.
     *
     * @return Additional task details.
     */
    protected String getDetails() {
        return "";
    }

    /**
     * Returns the formatted string representation of the task.
     *
     * @return A string containing the task type, status, description, and details.
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + getDetails();
    }
}