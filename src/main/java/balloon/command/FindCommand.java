package balloon.command;

import java.util.ArrayList;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import balloon.task.Task;

public class FindCommand implements Command {
    private String keyword;
    private ArrayList<Task> tasksFound;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasksFound = tasks.getTasksWithKeyword(keyword);
        // ui.showFindMessage(tasksFound);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        String out = "Here are the matching tasks in your list:";
        for (int i = 1; i <= tasksFound.size(); i++) {
            out += String.format("\n%d.%s", i, tasksFound.get(i - 1));
        }
        return out;
    }
}
