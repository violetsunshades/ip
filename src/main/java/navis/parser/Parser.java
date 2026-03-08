package navis.parser;

public class Parser {

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
