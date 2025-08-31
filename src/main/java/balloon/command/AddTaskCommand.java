package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import balloon.task.Task;

public abstract class AddTaskCommand implements Command {
    protected Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddTaskMessage(task, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
