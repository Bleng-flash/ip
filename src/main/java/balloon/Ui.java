package balloon;

import java.util.ArrayList;
import java.util.Scanner;

import balloon.task.Task;

/**
 * Provides methods related to UI, mostly displaying the message corresponding to different commands.
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);
    private static String greeting = "Hello! I'm BALLOON\nWhat can I do for you?";
    private static String exit = "Bye. Hope to see you again soon!";
    private static final String HORIZONTAL_LINE = "___________________________________________";

    public static String wrapInHorizontalLines(String s) {
        return HORIZONTAL_LINE + "\n" + s + "\n" + HORIZONTAL_LINE;
    }


    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showGreetingMessage() {
        System.out.println(wrapInHorizontalLines(greeting));
    }

    public void showExitMessage() {
        System.out.println(wrapInHorizontalLines(exit));
    }


    public void showErrorMessage(Exception e) {
        System.out.println(wrapInHorizontalLines(e.toString()));
    }

    public void printTasks(ArrayList<Task> tasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i, tasks.get(i - 1)));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void showAddTaskMessage(Task addedTask, int size) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + addedTask);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showDeleteTaskMessage(Task removedTask, int size) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + removedTask);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showMarkTaskMessage(Task markedTask) {
        System.out.println(wrapInHorizontalLines(
                "Nice! I've marked this task as done:\n\t" + markedTask));
    }

    public void showUnmarkTaskMessage(Task unmarkedTask) {
        System.out.println(wrapInHorizontalLines(
                "OK, I've marked this task as not done yet:\n\t" + unmarkedTask));
    }


}
