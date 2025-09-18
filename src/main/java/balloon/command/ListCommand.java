package balloon.command;

import java.util.ArrayList;

import balloon.Balloon;
import balloon.Storage;
import balloon.TaskList;
import balloon.task.Task;

public class ListCommand extends Command {
    ArrayList<Task> tasks;

    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon) {
        this.tasks = tasks.getTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        String out = "Here are the tasks in your list:";
        for (int i = 1; i <= tasks.size(); i++) {
            out += String.format("\n%d.%s", i, tasks.get(i - 1));
        }
        return out;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
