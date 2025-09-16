package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.exception.TaskNumberException;
import balloon.task.Task;

public class MarkCommand extends Command {
    private int taskNumber;
    Task markedTask;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws TaskNumberException {
        markedTask = tasks.markTask(taskNumber - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "Nice! I've marked this task as done:\n\t" + markedTask;
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + taskNumber;
    }
}
