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

    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE + greeting + HORIZONTAL_LINE + exit + HORIZONTAL_LINE);
    }
}
