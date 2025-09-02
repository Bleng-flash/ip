package balloon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import balloon.task.Task;
import balloon.task.Todo;
import balloon.task.Deadline;
import balloon.task.Event;

/**
 * Keeps track of task data across application sessions.
 * This class reads the list of tasks from a data file at application startup,
 * and writes updates back to the file at application termination.
 */
public class Storage {
    private final String filePath;


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {
        filePath = "";
    }

    /**
     * Loads the saved tasks from the save file.
     * If the file does not exist, returns an empty task list.
     *
     * @return a list of tasks represented by the save file.
     */
    public ArrayList<Task> loadSavedTasks() {
        ArrayList<Task> tasks = new ArrayList<>(100);
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
     *
     * @param tasks
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
     * Converts a line from the save file into a corresponding Task object.
     * <p>
     * Format for TODO: TODO | STATUS | DESCRIPTION
     * <p>
     * Format for DEADLINE: DEADLINE | STATUS | DESCRIPTION | BY
     * <p>
     * Format for EVENT: EVENT | STATUS | DESCRIPTION | FROM | TO
     * <p>
     * where STATUS is 1 if task is done; otherwise STATUS is 0.
     *
     * @param line a line from the save file.
     * @return a Task object that the line represents.
     */
    public Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;

        switch (type) {
            case "TODO":
                task = new Todo(description);
                break;
            case "DEADLINE":
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "EVENT":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
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
