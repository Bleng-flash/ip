package balloon.exception;

public class TaskNumberException extends BalloonException {
    public TaskNumberException() {
        super("The given task number does not exist!");
    }
}
