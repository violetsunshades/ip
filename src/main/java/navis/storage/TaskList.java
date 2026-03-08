package navis.storage;

import java.util.ArrayList;

import navis.exception.NavisException;
import navis.task.Task;

/**
 * Represents a list of tasks managed by the Navis application.
 * This class stores tasks in an ArrayList and provides operations
 * such as adding, deleting, marking, and searching tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with tasks loaded from storage.
     *
     * @param loadedTasks Tasks that were previously saved and loaded.
     */
    public TaskList(Task[] loadedTasks) {
        this.tasks = new ArrayList<>();

        for (Task task : loadedTasks) {
            if (task == null) {
                break;
            }
            this.tasks.add(task);
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks or unmarks a task as done.
     *
     * @param index The index of the task.
     * @param markAsDone True to mark the task as done, false to mark it as not done.
     * @throws NavisException If the index is invalid.
     */
    public void markTask(int index, boolean markAsDone) throws NavisException {
        if (!isValidIndex(index)) {
            throw new NavisException(" Please provide a valid task number.");
        }
        tasks.get(index).setDone(markAsDone);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to delete.
     * @return The task that was removed.
     * @throws NavisException If the index is invalid.
     */
    public Task deleteTask(int index) throws NavisException {
        if (!isValidIndex(index)) {
            throw new NavisException(" Please provide a valid task number.");
        }
        return tasks.remove(index);
    }

    /**
     * Finds tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return An array containing tasks that match the keyword.
     */
    public Task[] findTasks(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }

        return matches.toArray(new Task[0]);
    }

    /**
     * Checks whether the given index refers to a valid task.
     *
     * @param index The index to check.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns all tasks as an array.
     *
     * @return The array of tasks.
     */
    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }
}