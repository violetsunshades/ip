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