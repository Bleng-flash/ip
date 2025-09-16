package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.exception.CommandNotUndoableException;
import balloon.exception.SaveFileException;
import balloon.exception.TaskNumberException;

/**
 * Represents an executable user command in the application.
 * <p>
 * Each concrete subclass of {@code Command} encapsulates a specific action
 * that can be performed on the task list, such as adding, deleting, or marking
 * tasks as completed, using the execute method.
 * </p>
 */
public abstract class Command {
    public enum CommandType {
        LIST, EXIT,
        TODO, DEADLINE, EVENT,
        MARK, UNMARK, DELETE, FIND
    }

    /**
     * Executes the actions encapsulated within this Command.
     *
     * @param tasks the list of tasks.
     * @param storage the storage handler used to load or save tasks.
     * @throws TaskNumberException if the command refers to an invalid task number.
     */
    public abstract void execute(TaskList tasks, Storage storage)
            throws TaskNumberException, SaveFileException, CommandNotUndoableException;


    /**
     * @return true if command is Exit; false otherwise.
     */
    public abstract boolean isExit();

    /**
     *
     * @return the GUI's response to the command passed in by the user
     */
    public abstract String getString();

    /**
     * The save format will either be:
     * 1. NAME | taskNumber     (DeleteCommand, MarkCommand, UnmarkCommand)
     *    or
     * 2. NAME                  (for All other commands)
     * where NAME is the class name of the class that the command belongs to
     *
     * @return  the save format as a String of this command
     */
    public String toSaveFormat() {
        return this.getClass().getSimpleName();
    }

}
