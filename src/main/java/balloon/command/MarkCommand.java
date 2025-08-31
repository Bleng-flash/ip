package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import balloon.exception.TaskNumberException;
import balloon.task.Task;

public class MarkCommand implements Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNumberException {
        Task markedTask = tasks.markTask(taskNumber - 1);
        ui.showMarkTaskMessage(markedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
