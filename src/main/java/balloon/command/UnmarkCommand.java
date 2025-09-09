package balloon.command;

import java.util.concurrent.ConcurrentMap;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import balloon.exception.TaskNumberException;
import balloon.task.Task;

public class UnmarkCommand implements Command {
    private int taskNumber;
    private Task unmarkedTask;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNumberException {
        unmarkedTask = tasks.unmarkTask(taskNumber - 1);
        // ui.showUnmarkTaskMessage(unmarkedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "OK, I've marked this task as not done yet:\n\t" + unmarkedTask;
    }
}
