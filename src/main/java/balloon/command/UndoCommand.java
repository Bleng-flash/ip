package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.exception.SaveFileException;


public class UndoCommand extends Command {

    // when valid, the UndoCommand can always be translated to another existing Command
    private Command equivalentCommand;
    private String lastCommand;  // class name of the last command successfully executed

    /** Undo the last command successfully executed

     */
    @Override
    public void execute(TaskList tasks, Storage storage)
            throws CommandNotUndoableException, SaveFileException {
        String lastCommand = storage.getLastCommand();
        if (getString().isEmpty()) {
            throw new SaveFileException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "Previous command successfully undone!";
    }

    private void setEquivalentCommand() {

    }
}
