package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
