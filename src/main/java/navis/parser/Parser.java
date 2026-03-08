package navis.parser;

/**
 * Provides utility methods for parsing user input commands.
 * This class helps extract structured information such as task numbers
 * from raw command strings entered by the user.
 */
public class Parser {

    /**
     * Extracts the task number from a user command.
     * The task number is expected to be the second token in the command.
     * For example: "mark 2" or "delete 3".
     *
     * @param input The full command entered by the user.
     * @return The zero-based index of the task, or -1 if the input is invalid.
     */
    public static int parseTaskNumber(String input) {
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