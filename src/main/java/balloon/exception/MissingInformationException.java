package balloon.exception;

import balloon.command.Command.CommandType;

public class MissingInformationException extends BalloonException {
    private String requirement;
    private String commandName;

    public MissingInformationException(CommandType commandType) {
        switch(commandType) {
        case TODO:
            commandName = "<todo>";
            requirement = "have a non-empty description";
            break;
        case DEADLINE:
            commandName = "<deadline>";
            requirement = "have non-empty description and deadline";
            break;
        case EVENT:
            commandName = "<event>";
            requirement = "have non-empty description, from and to";
            break;
        case MARK:
            commandName = "<mark>";
            requirement = "be followed by an integer representing a task";
            break;
        case UNMARK:
            commandName = "<unmark>";
            requirement = "be followed by an integer representing a task";
            break;
        case DELETE:
            commandName = "<delete>";
            requirement = "be followed by an integer representing a task";
            break;
        case FIND:
            commandName = "<find>";
            requirement = "be followed by a String keyword";
        }
    }

    @Override
    public String toString() {
        return String.format("The %s command must %s", commandName, requirement);
    }
}
