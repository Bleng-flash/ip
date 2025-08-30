package balloon;
// the source root in Intellij is src/main/java and Java only cares about the path
// relative to the source root when determining the package

import java.util.ArrayList;
import java.util.Scanner;

import balloon.exception.StringConversionException;
import balloon.exception.TaskNumberException;
import balloon.task.Task;
import balloon.task.Todo;
import balloon.task.Deadline;
import balloon.task.Event;

import balloon.exception.BalloonException;
import balloon.exception.MissingInformationException;
import balloon.exception.NoCommandException;
import balloon.exception.UnknownCommandException;

public class Balloon {

    // System.out.print prints the string exactly as it is given
    // System.out.println prints the string + "\n" (adds a newline at the end)

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public enum Command {
        EXIT, LIST_TASKS,
        ADD_TODO, ADD_DEADLINE, ADD_EVENT,
        MARK_TASK, UNMARK_TASK, DELETE_TASK
    }

    public Balloon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadSavedTasks());
//        catch (BalloonException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public void addTask(Task task) {
        tasks.addTask(task);
        ui.showAddTaskMessage(task, tasks.getSize());
    }

    public void deleteTask(int taskNumber) throws TaskNumberException {
        Task deletedTask = tasks.deleteTask(taskNumber - 1);
        ui.showDeleteTaskMessage(deletedTask, tasks.getSize());
    }

    public void markTask(int taskNumber) throws TaskNumberException {
        Task markedTask = tasks.markTask(taskNumber - 1);
        ui.showMarkTaskMessage(markedTask);
    }

    public void unmarkTask(int taskNumber) throws TaskNumberException {
        Task unmarkedTask = tasks.unmarkTask(taskNumber - 1);
        ui.showUnmarkTaskMessage(unmarkedTask);
    }


    public static void main(String[] args) {
        new Balloon("./data/balloon.txt").run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in); // allows user to enter input via keyboard

        // Greet user at the start
        ui.showGreetingMessage();

        // If the scanner's input source is System.in (console input),
        // hasNextLine() will block and wait for user input if no input is currently available.
        // It will return true once the user provides input and presses Enter.
        while (true) {
            if (!sc.hasNextLine()) break;

            try {
                String input = sc.nextLine().trim();
                if (input.isBlank()) {
                    throw new NoCommandException();
                }

                // EXIT
                if (input.equals("bye")) {
                    ui.showExitMessage();
                    break;
                }

                // LIST_TASKS
                if (input.equals("list")) { // print all tasks
                    ui.printTasks(tasks.getTasks());
                    continue;
                }

                // ALl subsequent commands should take > 1 word

                if (input.equals("todo")) {
                    throw new MissingInformationException(Command.ADD_TODO);
                }
                if (input.equals("deadline")) {
                    throw new MissingInformationException(Command.ADD_DEADLINE);
                }
                if (input.equals("event")) {
                    throw new MissingInformationException(Command.ADD_EVENT);
                }
                if (input.equals("mark")) {
                    throw new MissingInformationException(Command.MARK_TASK);
                }
                if (input.equals("unmark")) {
                    throw new MissingInformationException(Command.UNMARK_TASK);
                }
                if (input.equals("delete")) {
                    throw new MissingInformationException(Command.DELETE_TASK);
                }

                // ADD_TODO
                if (input.startsWith("todo ")) {
                    String taskDescription = input.substring(5);
                    addTask(new Todo(taskDescription));
                    continue;
                    // Note that we do not need to check if taskDescription isBlank
                }

                // ADD_DEADLINE
                if (input.startsWith("deadline ")) {
                    String rest = input.substring(9);
                    String[] parts = rest.split(" /by ", 2); // max 2 parts split
                    if (parts.length == 2) {
                        String description = parts[0];
                        String by = parts[1];
                        if (description.isBlank() || by.isBlank()) {
                            throw new MissingInformationException(Command.ADD_DEADLINE);
                        } else {
                            addTask(new Deadline(description, by));
                        }
                    } else {
                        throw new MissingInformationException(Command.ADD_DEADLINE);
                    }
                    continue;
                }

                // ADD_EVENT
                if (input.startsWith("event ")) {
                    String rest = input.substring(6);

                    String[] parts1 = rest.split(" /from ", 2);
                    if (parts1.length < 2 || parts1[0].isBlank() || parts1[1].isBlank()) {
                        throw new MissingInformationException(Command.ADD_EVENT);
                    }
                    String description = parts1[0];

                    String[] parts2 = parts1[1].split(" /to ", 2);
                    if (parts2.length < 2 || parts2[0].isBlank() || parts2[1].isBlank()) {
                        throw new MissingInformationException(Command.ADD_EVENT);
                    }
                    String from = parts2[0];
                    String to = parts2[1];

                    addTask(new Event(description, from, to));

                    continue;
                }

                // MARK_TASK
                if (input.startsWith("mark ")) {
                    try {
                        String taskNumberString = input.substring(5);
                        int taskNumber = Integer.parseInt(taskNumberString);
                        markTask(taskNumber); // -1 to account for 0-based indexing
                    } catch (NumberFormatException e) {
                        throw new StringConversionException(Command.MARK_TASK);
                    }
                    continue;
                }

                // UNMARK_TASK
                if (input.startsWith("unmark ")) {
                    try {
                        String taskNumberString = input.substring(7);
                        int taskNumber = Integer.parseInt(taskNumberString);
                        unmarkTask(taskNumber);
                    } catch (NumberFormatException e) {
                        throw new StringConversionException(Command.UNMARK_TASK);
                    }
                    continue;
                }

                // DELETE_TASK
                if (input.startsWith("delete ")) {
                    try {
                        String taskNumberString = input.substring(7);
                        int taskNumber = Integer.parseInt(taskNumberString);
                        deleteTask(taskNumber);
                    } catch (NumberFormatException e) {
                        throw new StringConversionException(Command.DELETE_TASK);
                    }
                    continue;
                }

                // fallthrough means unknown command
                throw new UnknownCommandException();
            }
            catch (BalloonException e) {
                ui.showErrorMessage(e);
            }

        }
        sc.close();
        storage.save(tasks.getTasks()); // Save tasks into disk
    }
}
