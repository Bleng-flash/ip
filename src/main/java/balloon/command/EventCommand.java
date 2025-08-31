package balloon.command;

import balloon.task.Task;

public class EventCommand extends AddTaskCommand{
    public EventCommand(Task task) {
        super(task);
    }
}
