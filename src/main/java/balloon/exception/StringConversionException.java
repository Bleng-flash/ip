package balloon.exception;

import balloon.command.Command.CommandType;

public class StringConversionException extends BalloonException {
    private String commandName;

    public StringConversionException(CommandType commandType) {
        switch(commandType) {
        case MARK:
            commandName = "<mark>";
            break;
        case UNMARK:
            commandName = "<unmark>";
            break;
        case DELETE:
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
