import java.util.Scanner;

public class Navis {
    private static final String LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;

    private final String[] tasks = new String[MAX_TASKS];
    private final boolean[] isDone = new boolean[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args){
        new Navis().run();
    }
    private void run() {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        while(true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                printBye();
                break;
            }

            if (input.equals("list")) {
                printList();
                continue;
            }

            if (input.startsWith("mark ")){
                handleMarkCommand(input, true);
                continue;
            }

            if (input.startsWith("unmark ")){
                handleMarkCommand(input, false);
                continue;
            }

            addTask(input);
        }

        scanner.close();
    }

    private void printGreeting() {
        String logo =
                "N   N   AAAAA   V   V   IIIII   SSSSS\n"
                        + "NN  N   A   A   V   V     I    S    \n"
                        + "N N N   AAAAA    V V      I     SSSS \n"
                        + "N  NN   A   A     V       I        S \n"
                        + "N   N   A   A     V     IIIII   SSSSS\n";

        System.out.println(LINE);
        System.out.println("Hello! I'm Navis\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private void printBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private void printList(){
        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + formatTask(i));
        }
        System.out.println(LINE);
    }

    private void addTask(String task){
        if (task.isEmpty()) {
            printMessage(" Please type something.");
            return;
        }

        if (taskCount >= MAX_TASKS){
            printMessage("Sorry, your task list is full.");
            return;
        }

        tasks[taskCount] = task;
        isDone[taskCount] = false;
        taskCount++;

        System.out.println(LINE);
        System.out.println(" added: " + task);
        System.out.println(LINE);
    }

    private void handleMarkCommand(String input, boolean markAsDone) {
        int index = parseTaskNumber(input);
        if (index < 0 || index >= taskCount){
            printMessage(" Please provide a valid task number.");
            return;
        }

        isDone[index] = markAsDone;
        System.out.println(LINE);
        if (markAsDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + formatTask(index));
        System.out.println(LINE);
    }

    private int parseTaskNumber(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length < 2) {
            return -1;
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber - 1; // convert 1-based to 0-based
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String formatTask(int index) {
        String status = isDone[index] ? "[X] " : "[ ] ";
        return status + tasks[index];
    }

    private void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
}
