package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import javafx.application.Platform;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        // ui.showExitMessage();
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
