package balloon.command;

import balloon.task.Task;

public class DeadlineCommand extends AddTaskCommand {
    public DeadlineCommand(Task task) {
        super(task);
    }
}
