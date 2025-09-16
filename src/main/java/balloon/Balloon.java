package balloon;

import balloon.command.Command;
import balloon.exception.BalloonException;

/**
 * Provides the entry point function into the program.
 */
public class Balloon {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    private String commandType;

    /**
     * Constructor for Balloon.
     * @param filePath a string representing the relative path of the file loaded into storage.
     */
    public Balloon(String filePath) {
        ui = new Ui();
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
            command.execute(tasks, ui, storage);
            commandType = command.getClass().getSimpleName();
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
