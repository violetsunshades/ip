package navis.ui;

import navis.task.Task;

/**
 * Handles user interaction by displaying messages to the user.
 * This class is responsible for printing greetings, task updates,
 * errors, and other responses from the Navis application.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Displays the greeting message when the application starts.
     */
    public void showGreeting() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Navis");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Displays the farewell message when the user exits the application.
     */
    public void showBye() {
        System.out.println(LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(" OOPS!!!" + message);
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message after a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The updated total number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been marked or unmarked.
     *
     * @param task The task that was updated.
     * @param markAsDone True if the task was marked as done, false if marked as not done.
     */
    public void showTaskMarked(Task task, boolean markAsDone) {
        System.out.println(LINE);
        if (markAsDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    /**
     * Displays all tasks currently stored in the task list.
     *
     * @param tasks The array containing tasks.
     * @param taskCount The number of valid tasks in the array.
     */
    public void showTaskList(Task[] tasks, int taskCount) {
        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message after a task has been deleted.
     *
     * @param task The task that was removed.
     * @param taskCount The updated total number of tasks remaining.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays tasks that match a search query.
     *
     * @param matches An array of tasks that match the search keyword.
     */
    public void showFoundTasks(Task[] matches) {
        System.out.println(LINE);

        if (matches.length == 0) {
            System.out.println(" No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < matches.length; i++) {
                System.out.println(" " + (i + 1) + "." + matches[i]);
            }
        }

        System.out.println(LINE);
    }
}