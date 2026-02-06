import java.util.Scanner;

public class Navis {
    private static final String LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;

    private final Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args) {
        new Navis().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                printBye();
                break;
            }

            if (input.equals("list")) {
                printList();
                continue;
            }

            if (input.startsWith("mark ")) {
                handleMarkCommand(input, true);
                continue;
            }

            if (input.startsWith("unmark ")) {
                handleMarkCommand(input, false);
                continue;
            }

            if (input.startsWith("todo ")) {
                handleTodo(input);
                continue;
            }

            if (input.startsWith("deadline ")) {
                handleDeadline(input);
                continue;
            }

            if (input.startsWith("event ")) {
                handleEvent(input);
                continue;
            }

            // Optional: keep old Level-2 behavior (anything else becomes a Todo)
            addTask(new Todo(input));
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
        String description = input.substring("todo ".length()).trim();
        if (description.isEmpty()) {
            printMessage(" The description of a todo cannot be empty.");
            return;
        }
        addTask(new Todo(description));
    }

    private void handleDeadline(String input) {
        String remainder = input.substring("deadline ".length()).trim();
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
        String remainder = input.substring("event ".length()).trim();

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

    // ===== Task classes (A-Inheritance) =====

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
            return "[?]"; // overridden in subclasses
        }

        @Override
        public String toString() {
            return getTypeIcon() + getStatusIcon() + " " + description;
        }
    }

    private static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        protected String getTypeIcon() {
            return "[T]";
        }
    }

    private static class Deadline extends Task {
        private final String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        protected String getTypeIcon() {
            return "[D]";
        }

        @Override
        public String toString() {
            return getTypeIcon() + getStatusIcon() + " " + getDescription() + " (by: " + by + ")";
        }
    }

    private static class Event extends Task {
        private final String from;
        private final String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        protected String getTypeIcon() {
            return "[E]";
        }

        @Override
        public String toString() {
            return getTypeIcon() + getStatusIcon() + " " + getDescription()
                    + " (from: " + from + " to: " + to + ")";
        }
    }
}
