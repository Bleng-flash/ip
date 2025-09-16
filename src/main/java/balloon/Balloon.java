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
     * This is the entry point main function into the program.
     * @param args
     */
    public static void main(String[] args) {
        new Balloon("./data/balloon.txt").run();
    }

    public void run() {
        ui.showGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand().trim();
                Command command = Parser.parseUserInput(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BalloonException e) {
                ui.showErrorMessage(e);
            }
        }
        storage.save(tasks.getTasks());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseUserInput(input);
            assert command != null : "Parser should never return a null command";
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
