package balloon.command;

import balloon.Balloon;
import balloon.Storage;
import balloon.TaskList;
import balloon.exception.TaskNumberException;
import balloon.task.Task;

public abstract class AddTaskCommand extends Command {
    protected Task task;
    private int numberOfTasks;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon) {
        tasks.addTask(task);
        numberOfTasks = tasks.getSize();
        assert tasks.getSize() > 0 : "Task list should not be empty after adding a task";
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws TaskNumberException {
        tasks.deleteTask(tasks.getSize() - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public String getString() {
        return "Got it. I've added this task: \n\t" + task + "\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
