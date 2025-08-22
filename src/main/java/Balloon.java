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

    private static String greeting = "Hello! I'm BALLOON\nWhat can I do for you?\n";
    private static String exit = "Bye. Hope to see you again soon!\n";
    private static final String HORIZONTAL_LINE = "___________________________________________\n";
    private static ArrayList<Task> tasks = new ArrayList<>(100);

    public static String wrapInHorizontalLines(String s) {
        return HORIZONTAL_LINE + s + HORIZONTAL_LINE;
    }

    public static void printTasks() {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i, tasks.get(i - 1)));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println(wrapInHorizontalLines("Task number given does not exist\n"));
            return;
        }
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println(wrapInHorizontalLines("Nice! I've marked this task as done:\n" +
                task + "\n"));
    }

    public static void unmarkTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println(wrapInHorizontalLines("Task number given does not exist\n"));
            return;
        }
        Task task = tasks.get(index);
        task.unmark();
        System.out.println(wrapInHorizontalLines("OK, I've marked this task as not done yet:\n" +
                task + "\n"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // allows user to enter input via keyboard
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

            String[] parts = command.split("\\s+"); // split by any amount of consecutive white spaces
            if (parts.length == 2 && parts[0].equals("mark")) {
                int taskNumber = Integer.parseInt(parts[1]);
                markTask(taskNumber - 1); // -1 to account for 0-based indexing
            } else if (parts.length == 2 && parts[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(parts[1]);
                unmarkTask(taskNumber - 1);
            } else { // just add input task
                tasks.add(new Task(command));
                System.out.println(wrapInHorizontalLines("added: " + command + "\n"));
            }
        }
        sc.close();
    }
}
