package balloon.exception;

public class CommandNotUndoableException extends BalloonException {
    public CommandNotUndoableException() {
        super("The previous command is not undoable, or there is no previous command!");
    }
}
