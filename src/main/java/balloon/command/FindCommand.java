package balloon.command;

import java.util.ArrayList;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import balloon.task.Task;

public class FindCommand implements Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> tasksFound = tasks.getTasksWithKeyword(keyword);
        ui.showFindMessage(tasksFound);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
