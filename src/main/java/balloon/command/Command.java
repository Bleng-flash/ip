package balloon.command;

import balloon.Storage;
import balloon.TaskList;
import balloon.Ui;
import balloon.exception.TaskNumberException;

public interface Command {
    enum CommandType {
        LIST, EXIT,
        TODO, DEADLINE, EVENT,
        MARK, UNMARK, DELETE
    }
    void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNumberException;
    boolean isExit();
}
