package navis;

import java.io.IOException;
import java.util.Scanner;

import navis.exception.NavisException;
import navis.storage.Storage;
import navis.storage.TaskList;
import navis.task.Deadline;
import navis.task.Event;
import navis.task.Task;
import navis.task.Todo;
import navis.ui.Ui;
import navis.parser.Parser;

public class Navis {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String LIST_COMMAND = "list";
    private static final String BYE_COMMAND = "bye";
    private static final String DELETE_COMMAND = "delete";
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Navis() {
        this.ui = new Ui();
        this.storage = new Storage("data/navis.txt");
        this.taskList = loadTaskList();
    }

    public static void main(String[] args) {
        new Navis().run();
    }

    private TaskList loadTaskList() {
        try {
            Task[] loadedTasks = storage.loadTasks();
            return new TaskList(loadedTasks);
        } catch (IOException e) {
            ui.showError(" Error loading file: " + e.getMessage());
            return new TaskList();
        }
    }

    private void saveTasks() throws NavisException {
        try {
            storage.saveTasks(taskList.getTasks(), taskList.getTaskCount());
        } catch (IOException e) {
            throw new NavisException(" Error saving file: " + e.getMessage());
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showGreeting();

        boolean isRunning = true;
        while (isRunning && scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            try {
                isRunning = handleCommand(input);
            } catch (NavisException e) {
                ui.showError(e.getMessage());
            }
        }

        scanner.close();
    }

    private boolean handleCommand(String input) throws NavisException {
        if (input.equals(BYE_COMMAND)) {
            ui.showBye();
            return false;
        } else if (input.equals(LIST_COMMAND)) {
            ui.showTaskList(taskList.getTasks(), taskList.getTaskCount());
        } else if (input.startsWith(MARK_COMMAND)) {
            handleMarkCommand(input, true);
        } else if (input.startsWith(UNMARK_COMMAND)) {
            handleMarkCommand(input, false);
        } else if (input.startsWith(TODO_COMMAND)) {
            handleTodo(input);
        } else if (input.startsWith(DEADLINE_COMMAND)) {
            handleDeadline(input);
        } else if (input.startsWith(EVENT_COMMAND)) {
            handleEvent(input);
        } else if (input.startsWith(DELETE_COMMAND)) {
            handleDelete(input);
        } else {
            throw new NavisException(" I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    private void handleTodo(String input) throws NavisException {
        String description = input.substring(TODO_COMMAND.length()).trim();
        if (description.isEmpty()) {
            throw new NavisException(" The description of a todo cannot be empty.");
        }

        Task task = new Todo(description);
        taskList.addTask(task);
        saveTasks();
        ui.showTaskAdded(task, taskList.getTaskCount());
    }

    private void handleDeadline(String input) throws NavisException {
        String remainder = input.substring(DEADLINE_COMMAND.length()).trim();
        String[] parts = remainder.split("\\s+/by\\s+", 2);

        if (parts.length < 2) {
            throw new NavisException(" Please use: deadline <description> /by <by>");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new NavisException(" Please use: deadline <description> /by <by>");
        }

        Task task = new Deadline(description, by);
        taskList.addTask(task);
        saveTasks();
        ui.showTaskAdded(task, taskList.getTaskCount());
    }

    private void handleEvent(String input) throws NavisException {
        String remainder = input.substring(EVENT_COMMAND.length()).trim();

        int fromIndex = remainder.indexOf(" /from ");
        int toIndex = remainder.indexOf(" /to ");

        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            throw new NavisException(" Please use: event <description> /from <from> /to <to>");
        }

        String description = remainder.substring(0, fromIndex).trim();
        String from = remainder.substring(fromIndex + " /from ".length(), toIndex).trim();
        String to = remainder.substring(toIndex + " /to ".length()).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new NavisException(" Please use: event <description> /from <from> /to <to>");
        }

        Task task = new Event(description, from, to);
        taskList.addTask(task);
        saveTasks();
        ui.showTaskAdded(task, taskList.getTaskCount());
    }

    private void handleMarkCommand(String input, boolean markAsDone) throws NavisException {
        int index = Parser.parseTaskNumber(input);
        taskList.markTask(index, markAsDone);
        saveTasks();
        ui.showTaskMarked(taskList.getTask(index), markAsDone);
    }

    private void handleDelete(String input) throws NavisException {
        int index = Parser.parseTaskNumber(input);
        Task deletedTask = taskList.deleteTask(index);
        saveTasks();
        ui.showTaskDeleted(deletedTask, taskList.getTaskCount());
    }
}
