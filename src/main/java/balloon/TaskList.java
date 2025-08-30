package balloon;

import java.util.ArrayList;

import balloon.exception.TaskNumberException;
import balloon.task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task markTask(int index) throws TaskNumberException {
        if (index < 0 || index >= tasks.size()) {
           throw new TaskNumberException();
        }
        Task markedTask = tasks.get(index);
        markedTask.markAsDone();
        return markedTask;
    }

    public Task unmarkTask(int index) throws TaskNumberException{
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNumberException();
        }
        Task unmarkedTask = tasks.get(index);
        unmarkedTask.unmark();
        return unmarkedTask;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws TaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNumberException();
        }
        return tasks.remove(index);
    }

}
