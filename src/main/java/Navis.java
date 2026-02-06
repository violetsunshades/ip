import java.util.Scanner;

public class Navis {
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[100];
        int taskCount = 0;

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

        while(true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            }

            if (input.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < taskCount; i++){
                    System.out.println(" " + (i+1) + ". " + tasks[i]);
                }
                System.out.println(LINE);
                continue;
            }

            tasks[taskCount] = input;
            taskCount++;

            System.out.println(LINE);
            System.out.println(" added: "+ input);
            System.out.println(LINE);
        }

        scanner.close();
    }
}
