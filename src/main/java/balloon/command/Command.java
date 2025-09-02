package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import balloon.exception.TaskNumberException;

/**
 * Represents an executable user command in the application.
 * <p>
 * Each concrete subclass of {@code Command} encapsulates a specific action
 * that can be performed on the task list, such as adding, deleting, or marking
 * tasks as completed, using the execute method.
 * </p>
 */
public interface Command {
    enum CommandType {
        LIST, EXIT,
        TODO, DEADLINE, EVENT,
        MARK, UNMARK, DELETE, FIND
    }

    /**
     * Executes the actions encapsulated within this Command.
     *
     * @param tasks the list of tasks.
     * @param ui the user interface for displaying messages.
     * @param storage the storage handler used to load or save tasks.
     * @throws TaskNumberException if the command refers to an invalid task number.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNumberException;


    /**
     *
     * @return true if command is Exit; false otherwise.
     */
    boolean isExit();
}
