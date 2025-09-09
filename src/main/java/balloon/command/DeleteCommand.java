package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import balloon.exception.TaskNumberException;
import balloon.task.Task;

public class DeleteCommand implements Command {
    private int taskNumber;
    private Task deletedTask;
    private int numberOfTasks;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNumberException {
        deletedTask = tasks.deleteTask(taskNumber - 1);
        numberOfTasks = tasks.getSize();
        // ui.showDeleteTaskMessage(deletedTask, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "Noted. I've removed this task:\n\t" + deletedTask + "\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }
}
