package balloon.exception;

public class NoCommandException extends BalloonException {
    public NoCommandException() {
        super("You did not provide any command.");
    }
}
