package balloon.command;

import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;
import balloon.exception.SaveFileException;
import balloon.exception.TaskNumberException;
import balloon.exception.CommandNotUndoableException;

/**
 * The user can do a maximum of 1 UndoCommand in a row.
 * Not all commands can be undone.
 * In the case that the previous command is not undoable, a {@link CommandNotUndoableException}
 * will be thrown if the user tries to undo it.
 */
public class UndoCommand extends Command {

    /**
     * Undoes the last command successfully executed, if the last command is a
     * AddTaskCommand (TodoCommand, DeadlineCommand, or EventCommand) ,
     * DeleteCommand, MarkCommand or UnmarkCommand
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon)
            throws TaskNumberException, CommandNotUndoableException, SaveFileException {
        Command lastCommand = balloon.getLastCommand();
        if (lastCommand == null || !lastCommand.isUndoable()) {
            throw new CommandNotUndoableException();
        }
        lastCommand.undo(tasks, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "Previous command successfully undone!";
    }

    /**
     * An UndoCommand is not undoable!
     */
    @Override
    public boolean isUndoable() {
        return false;
    }
}
