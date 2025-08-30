package balloon.exception;

import balloon.Balloon.Command;

public class MissingInformationException extends BalloonException {
    private String requirement;
    private String commandName;

    public MissingInformationException(Command commandType) {
        switch(commandType) {
        case ADD_TODO:
            commandName = "<todo>";
            requirement = "have a non-empty description";
            break;
        case ADD_DEADLINE:
            commandName = "<deadline>";
            requirement = "have non-empty description and deadline";
            break;
        case ADD_EVENT:
            commandName = "<event>";
            requirement = "have non-empty description, from and to";
            break;
        case MARK_TASK:
            commandName = "<mark>";
            requirement = "be followed by an integer representing a task";
            break;
        case UNMARK_TASK:
            commandName = "<unmark>";
            requirement = "be followed by an integer representing a task";
            break;
        case DELETE_TASK:
            commandName = "<delete>";
            requirement = "be followed by an integer representing a task";
            break;
        }
    }

    @Override
    public String toString() {
        return String.format("The %s command must %s", commandName, requirement);
    }
}
