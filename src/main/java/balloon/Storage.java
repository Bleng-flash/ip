package balloon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import balloon.command.Command;
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
            if (sc.hasNextLine()) {
                sc.nextLine();  // skip the first line (last executed command)
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseLineForTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        assert tasks != null : "Loaded tasks should not be a null";

        return tasks;
    }

    /**
     * Saves the last executed command, followed by the list of tasks to the save file.
     * The lastCommand save format will either be:
     *  1. NAME | taskNumber     (DeleteCommand, MarkCommand, UnmarkCommand)
     *      or
     *  2. NAME                  (for All other commands)
     *
     * @param tasks
     * @param lastCommand   the last successfully executed command
     */
    public void save(ArrayList<Task> tasks, Command lastCommand) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Ensure ./data/ directory exists

        try (FileWriter fw = new FileWriter(file)) {
            // Write the last command
            fw.write(lastCommand.toSaveFormat() + System.lineSeparator());

            // Write the tasks currently in the list
            for (Task t : tasks) {
                fw.write(t.toSaveFormat() + System.lineSeparator());
            }
            assert new File(filePath).exists() : "Save file should exist after saving";
            // the above line does not create a file
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Converts a line representing a task from the save file into a corresponding Task object.
     * <p>
     * Format for TODO: TODO | STATUS | DESCRIPTION
     * <p>
     * Format for DEADLINE: DEADLINE | STATUS | DESCRIPTION | BY
     * <p>
     * Format for EVENT: EVENT | STATUS | DESCRIPTION | FROM | TO
     * <p>
     * where STATUS is 1 if task is done; otherwise STATUS is 0.
     *
     * @param line  a line that represents a task from the save file.
     * @return      a Task object that the line represents.
     */
    public Task parseLineForTask(String line) {
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

    /**
     *
     * @return  the first line in the save file, which we assume is last executed command
     */
    public String getLastCommand() {
        try (Scanner sc = new Scanner(new File(filePath))) {
            if (sc.hasNextLine()) {
                String firstLine = sc.nextLine();
                return firstLine;
            } else {
                return ""; // occurs when the save fiile is empty
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return "";
        }
    }
}
