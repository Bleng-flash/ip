package balloon.exception;

import balloon.Balloon;

public class MissingInformationException extends BalloonException {
    private Balloon.Command commandType;
    private String requirement;
    private String commandName;

    public MissingInformationException(Balloon.Command commandType) {
        this.commandType= commandType;
        /* We should use == to compare enums. Each enum constant is a singleton:
           there is exactly one instance of each enum constant in the JVM.
           == checks reference equality, which works perfectly for enums */

        if (commandType == Balloon.Command.ADD_TODO) {
            commandName = "<todo>";
            requirement = "have a non-empty description";
        } else if (commandType == Balloon.Command.ADD_DEADLINE) {
            commandName = "<deadline>";
            requirement = "have non-empty description and deadline";
        } else if (commandType == Balloon.Command.ADD_EVENT) {
            commandName = "<event>";
            requirement = "have non-empty description, from and to";
        } else if (commandType == Balloon.Command.MARK_TASK) {
            commandName = "<mark>";
            requirement = "be followed by an integer representing a task";
        } else if (commandType == Balloon.Command.UNMARK_TASK) {
            commandName = "<unmark>";
            requirement = "be followed by an integer representing a task";
        } else if (commandType == Balloon.Command.DELETE_TASK) {
            commandName = "<delete>";
            requirement = "be followed by an integer representing a task";
        }
    }

    @Override
    public String toString() {
        return String.format("The %s command must %s", commandName, requirement);
    }
}
