public class TaskList {
    private static final int MAX_TASKS = 100;

    private final Task[] tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

    public boolean addTask(Task task) {
        if (taskCount >= MAX_TASKS) {
            return false;
        }

        tasks[taskCount] = task;
        taskCount++;
        return true;
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