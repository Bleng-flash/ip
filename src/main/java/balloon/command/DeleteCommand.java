package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import balloon.exception.TaskNumberException;
import balloon.task.Task;

public class DeleteCommand implements Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNumberException {
        Task deletedTask = tasks.deleteTask(taskNumber - 1);
        ui.showDeleteTaskMessage(deletedTask, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
