package navis.storage;

import navis.task.Task;
import navis.exception.NavisException;

/**
 * Represents a list of tasks managed by the Navis application.
 * This class stores tasks in an array and provides operations
 * such as adding, deleting, marking, and searching tasks.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;

    private final Task[] tasks;
    private int taskCount;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

    /**
     * Creates a task list initialized with tasks loaded from storage.
     *
     * @param loadedTasks Tasks that were previously saved and loaded.
     */
    public TaskList(Task[] loadedTasks) {
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;

        for (Task task : loadedTasks) {
            if (task == null) {
                break;
            }
            this.tasks[taskCount] = task;
            taskCount++;
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @throws NavisException If the task list has reached its maximum capacity.
     */
    public void addTask(Task task) throws NavisException {
        if (taskCount >= MAX_TASKS) {
            throw new NavisException(" Sorry, your task list is full.");
        }

        tasks[taskCount] = task;
        taskCount++;
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
        tasks[index].setDone(markAsDone);
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

        Task deletedTask = tasks[index];

        for (int i = index; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }

        tasks[taskCount - 1] = null;
        taskCount--;

        return deletedTask;
    }

    /**
     * Finds tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return An array containing tasks that match the keyword.
     */
    public Task[] findTasks(String keyword) {
        Task[] matches = new Task[MAX_TASKS];
        int matchCount = 0;

        for (int i = 0; i < taskCount; i++) {
            if (tasks[i].toString().toLowerCase().contains(keyword.toLowerCase())) {
                matches[matchCount] = tasks[i];
                matchCount++;
            }
        }

        Task[] result = new Task[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = matches[i];
        }

        return result;
    }

    /**
     * Checks whether the given index refers to a valid task.
     *
     * @param index The index to check.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < taskCount;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks[index];
    }

    /**
     * Returns the underlying array storing all tasks.
     *
     * @return The array of tasks.
     */
    public Task[] getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return taskCount;
    }
}