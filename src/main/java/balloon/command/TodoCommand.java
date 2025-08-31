package balloon.command;

import balloon.task.Task;

public class TodoCommand extends AddTaskCommand {
    public TodoCommand(Task task) {
        super(task);
    }
}
