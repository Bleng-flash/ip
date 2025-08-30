package balloon.exception;

import balloon.Balloon.Command;

public class StringConversionException extends BalloonException {
    String commandName;

    public StringConversionException(Command command) {
        switch(command) {
        case MARK_TASK:
            commandName = "<mark>";
            break;
        case UNMARK_TASK:
            commandName = "<unmark>";
            break;
        case DELETE_TASK:
            commandName = "<delete";
            break;
        default:
            commandName = "";
        }
    }

    @Override
    public String toString() {
        return commandName + " command has to be followed by an integer only";
    }
}
