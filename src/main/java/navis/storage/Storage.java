package navis.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;

import navis.task.Deadline;
import navis.task.Event;
import navis.task.Task;
import navis.task.Todo;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Task[] loadTasks() throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            file.createNewFile();
            return new Task[100];
        }

        Task[] tasks = new Task[100];
        int index = 0;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                continue;
            }

            String[] parts = line.split(" \\| ");

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;

            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                continue;
            }

            task.setDone(isDone);
            tasks[index] = task;
            index++;
        }

        scanner.close();
        return tasks;
    }

    public void saveTasks(Task[] tasks, int taskCount) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        FileWriter writer = new FileWriter(file);

        for (int i = 0; i < taskCount; i++) {
            Task task = tasks[i];

            if (task instanceof Todo) {
                writer.write("T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + System.lineSeparator());
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                writer.write("D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription()
                        + " | " + deadline.getBy() + System.lineSeparator());
            } else if (task instanceof Event) {
                Event event = (Event) task;
                writer.write("E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription()
                        + " | " + event.getFrom() + " | " + event.getTo() + System.lineSeparator());
            }
        }

        writer.close();
    }
}