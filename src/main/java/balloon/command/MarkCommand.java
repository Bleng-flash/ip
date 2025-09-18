package balloon.command;

import balloon.Balloon;
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
    public void execute(TaskList tasks, Storage storage, Balloon balloon) throws TaskNumberException {
        markedTask = tasks.markTask(taskNumber - 1);
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws TaskNumberException {
        tasks.unmarkTask(taskNumber - 1);
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
    public boolean isUndoable() {
        return true;
    }
}
