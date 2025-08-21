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
    private static ArrayList<String> tasks = new ArrayList<>(100);

    public static String wrapInHorizontalLines(String s) {
        return HORIZONTAL_LINE + s + HORIZONTAL_LINE;
    }

    public static void printTasks() {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i, tasks.get(i - 1)));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // allows user to enter input via keyboard
        System.out.println(wrapInHorizontalLines(greeting));

        // If the scanner's input source is System.in (console input),
        // hasNextLine() will block and wait for user input if no input is currently available.
        // It will return true once the user provides input and presses Enter.
        while (true) {
            if (!sc.hasNextLine()) break;
            String command = sc.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                System.out.println(wrapInHorizontalLines(exit));
                break;
            }

            if (command.equalsIgnoreCase("list")) { // print all tasks
                printTasks();
            } else { // just add input task
                tasks.add(command);
                System.out.println(wrapInHorizontalLines("added: " + command + "\n"));
            }
        }
        sc.close();
    }
}
