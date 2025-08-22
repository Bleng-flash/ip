import java.util.ArrayList;
import java.util.Scanner;

public class Balloon {
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
     */

    // System.out.print prints the string exactly as it is given
    // System.out.println prints the string + "\n" (adds a newline at the end)

    public enum Command {
        EXIT, LIST_TASKS, ADD_TODO, ADD_DEADLINE, ADD_EVENT, MARK_TASK, UNMARK_TASK, DELETE_TASK
    }

    private static String greeting = "Hello! I'm BALLOON\nWhat can I do for you?";
    private static String exit = "Bye. Hope to see you again soon!";
    private static final String HORIZONTAL_LINE = "___________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>(100);

    public static String wrapInHorizontalLines(String s) {
        return HORIZONTAL_LINE + "\n" + s + "\n" + HORIZONTAL_LINE;
    }

    public static void printTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i, tasks.get(i - 1)));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println(wrapInHorizontalLines("Task number given does not exist"));
            return;
        }
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println(wrapInHorizontalLines("Nice! I've marked this task as done:\n\t" +
                task));
    }

    public static void unmarkTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println(wrapInHorizontalLines("Task number given does not exist"));
            return;
        }
        Task task = tasks.get(index);
        task.unmark();
        System.out.println(wrapInHorizontalLines("OK, I've marked this task as not done yet:\n\t" +
                task));
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println(wrapInHorizontalLines("Task number given does not exist"));
            return;
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + tasks.remove(index));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }
    public static void printErrorMessage(Exception e) {
        System.out.println(wrapInHorizontalLines(e.toString()));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // allows user to enter input via keyboard

        // Greet user at the start
        System.out.println(wrapInHorizontalLines(greeting));

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
                    System.out.println(wrapInHorizontalLines(exit));
                    break;
                }

                // LIST_TASKS
                if (input.equals("list")) { // print all tasks
                    printTasks();
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
                        markTask(taskNumber - 1); // -1 to account for 0-based indexing
                    } catch (NumberFormatException e) {
                        // if taskNumberString cannot be converted to int
                        System.out.println(wrapInHorizontalLines("<mark> command has to be followed" +
                                " by an integer only"));
                    }
                    continue;
                }

                // UNMARK_TASK
                if (input.startsWith("unmark ")) {
                    try {
                        String taskNumberString = input.substring(7);
                        int taskNumber = Integer.parseInt(taskNumberString);
                        unmarkTask(taskNumber - 1);
                    } catch (NumberFormatException e) {
                        System.out.println(wrapInHorizontalLines("<unmark> command has to be " +
                                "followed by an integer only"));
                    }
                    continue;
                }

                if (input.startsWith("delete ")) {
                    try {
                        String taskNumberString = input.substring(7);
                        int taskNumber = Integer.parseInt(taskNumberString);
                        deleteTask(taskNumber - 1);
                    } catch (NumberFormatException e) {
                        System.out.println(wrapInHorizontalLines("<delete> command has to be " +
                                "followed by an integer only"));
                    }
                    continue;
                }

                // fallthrough means unknown command
                throw new UnknownCommandException();
            }

            catch (BalloonException e) {
                printErrorMessage(e);
            }

        }
        sc.close();
    }
}
