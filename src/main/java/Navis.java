import java.util.Scanner;

public class Navis {
    private static final String TODO_COMMAND = "todo ";
    private static final String DEADLINE_COMMAND = "deadline ";
    private static final String EVENT_COMMAND = "event ";
    private static final String MARK_COMMAND = "mark ";
    private static final String UNMARK_COMMAND = "unmark ";

    private final Ui ui;
    private final TaskList taskList;

    public Navis() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    public static void main(String[] args) {
        new Navis().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showGreeting();

        boolean isRunning = true;
        while (isRunning && scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            isRunning = handleCommand(input);
        }

        scanner.close();
    }

    private boolean handleCommand(String input) {
        if (input.equals("bye")) {
            ui.showBye();
            return false;
        } else if (input.equals("list")) {
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
        } else {
            ui.showMessage(" Sorry, I don't know what that means.");
        }
        return true;
    }

    private void handleTodo(String input) {
        String description = input.substring(TODO_COMMAND.length()).trim();
        if (description.isEmpty()) {
            ui.showMessage(" The description of a todo cannot be empty.");
            return;
        }

        Task task = new Todo(description);
        boolean isAdded = taskList.addTask(task);
        if (!isAdded) {
            ui.showMessage(" Sorry, your task list is full.");
            return;
        }

        ui.showTaskAdded(task, taskList.getTaskCount());
    }

    private void handleDeadline(String input) {
        String remainder = input.substring(DEADLINE_COMMAND.length()).trim();
        String[] parts = remainder.split("\\s+/by\\s+", 2);

        if (parts.length < 2) {
            ui.showMessage(" Please use: deadline <description> /by <by>");
            return;
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            ui.showMessage(" Please use: deadline <description> /by <by>");
            return;
        }

        Task task = new Deadline(description, by);
        boolean isAdded = taskList.addTask(task);
        if (!isAdded) {
            ui.showMessage(" Sorry, your task list is full.");
            return;
        }

        ui.showTaskAdded(task, taskList.getTaskCount());
    }

    private void handleEvent(String input) {
        String remainder = input.substring(EVENT_COMMAND.length()).trim();

        int fromIndex = remainder.indexOf(" /from ");
        int toIndex = remainder.indexOf(" /to ");

        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            ui.showMessage(" Please use: event <description> /from <from> /to <to>");
            return;
        }

        String description = remainder.substring(0, fromIndex).trim();
        String from = remainder.substring(fromIndex + " /from ".length(), toIndex).trim();
        String to = remainder.substring(toIndex + " /to ".length()).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            ui.showMessage(" Please use: event <description> /from <from> /to <to>");
            return;
        }

        Task task = new Event(description, from, to);
        boolean isAdded = taskList.addTask(task);
        if (!isAdded) {
            ui.showMessage(" Sorry, your task list is full.");
            return;
        }

        ui.showTaskAdded(task, taskList.getTaskCount());
    }

    private void handleMarkCommand(String input, boolean markAsDone) {
        int index = parseTaskNumber(input);
        if (!taskList.isValidIndex(index)) {
            ui.showMessage(" Please provide a valid task number.");
            return;
        }

        Task task = taskList.getTask(index);
        task.setDone(markAsDone);
        ui.showTaskMarked(task, markAsDone);
    }

    private int parseTaskNumber(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length < 2) {
            return -1;
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}