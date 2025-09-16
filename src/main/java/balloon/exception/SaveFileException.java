package balloon.exception;

public class SaveFileException extends BalloonException {
    public SaveFileException() {
        super("The save file either does not exist or is empty");
    }
}
