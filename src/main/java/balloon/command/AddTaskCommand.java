package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.task.Task;

public abstract class AddTaskCommand implements Command {
    protected Task task;
    private int numberOfTasks;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        numberOfTasks = tasks.getSize();
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
}
