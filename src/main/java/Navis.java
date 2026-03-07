import java.util.Scanner;

public class Navis {
    private static final String LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static final String TODO_COMMAND = "todo ";
    private static final String DEADLINE_COMMAND = "deadline ";
    private static final String EVENT_COMMAND = "event ";
    private static final String MARK_COMMAND = "mark ";
    private static final String UNMARK_COMMAND = "unmark ";

    private final Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args) {
        new Navis().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        printGreeting();

        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                printBye();
                isRunning = false;
            } else if (input.equals("list")) {
                printList();
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
                printMessage(" Sorry, I don't know what that means.");
            }
        }

        scanner.close();
    }

    private void printGreeting() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Navis");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    private void printBye() {
        System.out.println(LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private void printList() {
        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
        System.out.println(LINE);
    }

    private void handleTodo(String input) {
        String description = input.substring(TODO_COMMAND.length()).trim();
        if (description.isEmpty()) {
            printMessage(" The description of a todo cannot be empty.");
            return;
        }

        addTask(new Todo(description));
    }

    private void handleDeadline(String input) {
        String remainder = input.substring(DEADLINE_COMMAND.length()).trim();
        String[] parts = remainder.split("\\s+/by\\s+", 2);

        if (parts.length < 2) {
            printMessage(" Please use: deadline <description> /by <by>");
            return;
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            printMessage(" Please use: deadline <description> /by <by>");
            return;
        }

        addTask(new Deadline(description, by));
    }

    private void handleEvent(String input) {
        String remainder = input.substring(EVENT_COMMAND.length()).trim();

        int fromIndex = remainder.indexOf(" /from ");
        int toIndex = remainder.indexOf(" /to ");

        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            printMessage(" Please use: event <description> /from <from> /to <to>");
            return;
        }

        String description = remainder.substring(0, fromIndex).trim();
        String from = remainder.substring(fromIndex + " /from ".length(), toIndex).trim();
        String to = remainder.substring(toIndex + " /to ".length()).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            printMessage(" Please use: event <description> /from <from> /to <to>");
            return;
        }

        addTask(new Event(description, from, to));
    }

    private void addTask(Task task) {
        if (taskCount >= MAX_TASKS) {
            printMessage(" Sorry, your task list is full.");
            return;
        }

        tasks[taskCount] = task;
        taskCount++;

        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }

    private void handleMarkCommand(String input, boolean markAsDone) {
        int index = parseTaskNumber(input);
        if (index < 0 || index >= taskCount) {
            printMessage(" Please provide a valid task number.");
            return;
        }

        tasks[index].setDone(markAsDone);

        System.out.println(LINE);
        if (markAsDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + tasks[index]);
        System.out.println(LINE);
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

    private void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    private static class Task {
        private final String description;
        private boolean isDone;

        protected Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void setDone(boolean isDone) {
            this.isDone = isDone;
        }

        protected String getStatusIcon() {
            return isDone ? "[X]" : "[ ]";
        }

        protected String getDescription() {
            return description;
        }

        protected String getTypeIcon() {
            return "[?]";
        }

        protected String getDetails() {
            return "";
        }

        @Override
        public String toString() {
            return getTypeIcon() + getStatusIcon() + " " + description + getDetails();
        }
    }

    private static class Todo extends Task {
        protected Todo(String description) {
            super(description);
        }

        @Override
        protected String getTypeIcon() {
            return "[T]";
        }
    }

    private static class Deadline extends Task {
        private final String by;

        protected Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        protected String getTypeIcon() {
            return "[D]";
        }

        @Override
        protected String getDetails() {
            return " (by: " + by + ")";
        }
    }

    private static class Event extends Task {
        private final String from;
        private final String to;

        protected Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        protected String getTypeIcon() {
            return "[E]";
        }

        @Override
        protected String getDetails() {
            return " (from: " + from + " to: " + to + ")";
        }
    }
}