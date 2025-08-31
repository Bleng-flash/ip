package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
