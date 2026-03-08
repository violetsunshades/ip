package navis.storage;

import navis.task.Task;
import navis.exception.NavisException;

public class TaskList {
    private static final int MAX_TASKS = 100;

    private final Task[] tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

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

    public void addTask(Task task) throws NavisException {
        if (taskCount >= MAX_TASKS) {
            throw new NavisException(" Sorry, your task list is full.");
        }

        tasks[taskCount] = task;
        taskCount++;
    }

    public void markTask(int index, boolean markAsDone) throws NavisException {
        if (!isValidIndex(index)) {
            throw new NavisException(" Please provide a valid task number.");
        }
        tasks[index].setDone(markAsDone);
    }

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

    public boolean isValidIndex(int index) {
        return index >= 0 && index < taskCount;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }
}