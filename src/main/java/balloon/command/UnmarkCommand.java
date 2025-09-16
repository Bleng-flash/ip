package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.exception.TaskNumberException;
import balloon.task.Task;

public class UnmarkCommand extends Command {
    private int taskNumber;
    private Task unmarkedTask;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws TaskNumberException {
        unmarkedTask = tasks.unmarkTask(taskNumber - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "OK, I've marked this task as not done yet:\n\t" + unmarkedTask;
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + taskNumber;
    }
}
