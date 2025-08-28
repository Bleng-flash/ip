import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the save file.
     * If the file does not exist, returns an empty task list.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            // No save file yet, return empty list
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseLine(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the save file.
     */
    public void save(ArrayList<Task> tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Ensure ./data/ directory exists

        try (FileWriter fw = new FileWriter(file)) {
            for (Task t : tasks) {
                fw.write(t.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Converts a line from the save file into a Task object.
     * Format for TODO: TYPE | STATUS | DESCRIPTION
     * Format for DEADLINE: TYPE | STATUS | DESCRIPTION | BY
     * Format for EVENT: TYPE | STATUS | DESCRIPTION | FROM | TO
     */
    private Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        String by = null;
        String from = null;
        String to = null;

        if (type.equals("DEADLINE")) {
            by = parts[3];
        }
        if (type.equals("EVENT")) {
            from = parts[3];
            to = parts[4];
        }

        Task task;
        switch (type) {
            case "TODO":
                task = new Todo(description);
                break;
            case "DEADLINE":
                task = new Deadline(description, by); // assume string date for now
                break;
            case "EVENT":
                task = new Event(description, from, to); // assume string date for now
                break;
            default:
                throw new IllegalArgumentException("Unknown task type in save file: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
