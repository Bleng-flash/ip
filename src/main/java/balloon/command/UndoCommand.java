package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.exception.SaveFileException;
import balloon.exception.TaskNumberException;
import balloon.exception.CommandNotUndoableException;


public class UndoCommand extends Command {

    /**
     * Undoes the last command successfully executed
     */
    @Override
    public void execute(TaskList tasks, Storage storage)
            throws TaskNumberException, CommandNotUndoableException, SaveFileException {
        String lastCommandSaveFormat = storage.getLastCommand();
        if (getString().isEmpty()) {
            throw new SaveFileException();
        }

        // splits using " | " as the delimiter
        String[] parts = lastCommandSaveFormat.split(" \\| ");
        String lastCommandName = parts[0];
        Command equivalentCommand = null;
        int taskNumber = -1;

        switch (lastCommandName) {
        case "MarkCommand":
            taskNumber = Integer.parseInt(parts[1]);
            equivalentCommand = new UnmarkCommand(taskNumber);
            break;
        case "UnmarkCommand":
            taskNumber = Integer.parseInt(parts[1]);
            equivalentCommand = new MarkCommand(taskNumber);
            break;

        // If the previous command was adding a task, then we simply delete the last task
        // on the list
        case "TodoCommand":
        case "DeadlineCommand":
        case "EventCommand":
            equivalentCommand = new DeleteCommand(tasks.getSize());
            break;

        // all previous commands below this point should not be undoable
        case "DeleteCommand": // For now, exclude DeleteCommand from undoable commands
        case "UndoCommand":
        case "ListCommand":
        case "ExitCommand":
        case "FindCommand":
            throw new CommandNotUndoableException();
        default:
            System.out.println("should not reach here");
        }

        // when valid, the UndoCommand can always be translated to another existing Command
        equivalentCommand.execute(tasks, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "Previous command successfully undone!";
    }

}
