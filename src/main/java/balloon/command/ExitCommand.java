package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import javafx.application.Platform;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Platform.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getString() {
        return "Bye. Hope to see you again soon!";
    }
}
