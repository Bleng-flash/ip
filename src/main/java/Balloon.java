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
            String command = sc.nextLine().trim(); // removes leading and trailing spaces

            if (command.equals("bye")) {
                System.out.println(wrapInHorizontalLines(exit));
                break;
            }

            if (command.equals("list")) { // print all tasks
                printTasks();
                continue;
            }

            if (command.startsWith("todo ")) {
                String taskDescription = command.substring(5);
                if (taskDescription.isBlank()) {
                    // isBlank returns true iff string is empty or only contains whitespaces only
                    System.out.println(wrapInHorizontalLines("The description of a todo cannot be " +
                            "blank!"));
                } else {
                    addTask(new Todo(taskDescription));
                }
                continue;
            }

            if (command.startsWith("deadline ")) {
                String rest = command.substring(9);
                String[] parts = rest.split(" /by ", 2); // max 2 parts split
                if (parts.length == 2) {
                    String description = parts[0];
                    String by = parts[1];
                    if (description.isBlank() || by.isBlank()) {
                        System.out.println(wrapInHorizontalLines("The description and the " +
                                "deadline of a deadline task cannot be blank!"));
                    } else {
                        addTask(new Deadline(description, by));
                    }
                } else {
                    System.out.println(wrapInHorizontalLines("Invalid deadline command. " +
                            "Missing ' /by '"));
                }
                continue;
            }

            if (command.startsWith("event ")) {
                int startIndexFrom = command.indexOf(" /from ");
                int startIndexTo = command.indexOf(" /to ");

                if (startIndexFrom == -1 || startIndexTo == -1) {
                    System.out.println(wrapInHorizontalLines("Invalid event command. " +
                            "Missing ' /from ' or ' /to "));
                } else if (startIndexFrom > startIndexTo) {
                    System.out.println(wrapInHorizontalLines("Invalid order. " +
                            "Use '/from' before '/to'"));
                }
                else {
                    String description = command.substring(6, startIndexFrom);
                    String from = command.substring(startIndexFrom + 7, startIndexTo);
                    String to = command.substring(startIndexTo + 5);
                    if (description.isBlank() || from.isBlank() || to.isBlank()) {
                        System.out.println(wrapInHorizontalLines("The description, from, and to" +
                                " of an event cannot be blank!"));
                    } else {
                        addTask(new Event(description, from, to));
                    }
                }
                continue;
            }

            if (command.startsWith("mark ")) {
                try {
                    String taskNumberString = command.substring(5);
                    int taskNumber = Integer.parseInt(taskNumberString);
                    markTask(taskNumber - 1); // -1 to account for 0-based indexing
                } catch (NumberFormatException e) { // if taskNumberString cannot be converted to int
                    System.out.println("mark command has to be followed by an integer only");
                }
                continue;
            }

            if (command.startsWith("unmark ")) {
                try {
                    String taskNumberString = command.substring(7);
                    int taskNumber = Integer.parseInt(taskNumberString);
                    unmarkTask(taskNumber - 1); // -1 to account for 0-based indexing
                } catch (NumberFormatException e) { // if taskNumberString cannot be converted to int
                    System.out.println("unmark command has to be followed by an integer only");
                }
                continue;
            }

            System.out.println(wrapInHorizontalLines("Invalid command provided."));
        }
        sc.close();
    }
}
