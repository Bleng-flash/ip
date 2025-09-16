package balloon;

import balloon.command.Command;
import balloon.exception.BalloonException;

/**
 * Provides the entry point function into the program.
 */
public class Balloon {

    private TaskList tasks;
    private Storage storage;

    private String commandType;

    /**
     * Constructor for Balloon.
     * @param filePath a string representing the relative path of the file loaded into storage.
     */
    public Balloon(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadSavedTasks());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String trimmedInput = input.trim();
            Command command = Parser.parseUserInput(trimmedInput);
            command.execute(tasks, storage);
            assert command != null : "Parser should never return a null command";

            commandType = command.getClass().getSimpleName();

            // Save tasks immediately after each command
            storage.save(tasks.getTasks(), command);

            return command.getString();
        } catch (BalloonException e) {
            commandType = "ExceptionInduced";
            return "Error: " + e;
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
