package balloon;

import java.util.ArrayList;

import balloon.exception.TaskNumberException;
import balloon.task.Task;

/**
 * Represents a list of tasks that the user has.
 * Certain commands will invoke methods from this to modify the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     *
     * @return number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a given task as done.
     *
     * @param index position of the task in the list.
     * @return the Task marked.
     * @throws TaskNumberException if given index does not correspond to an element in the tasklist.
     */
    public Task markTask(int index) throws TaskNumberException {
        if (index < 0 || index >= tasks.size()) {
           throw new TaskNumberException();
        }
        Task markedTask = tasks.get(index);
        markedTask.markAsDone();
        return markedTask;
    }

    /**
     * Unmarks a given task; meaning it is not done.
     *
     * @param index position of the task in the list.
     * @return the Task unmarked.
     * @throws TaskNumberException if given index does not correspond to an element in the tasklist.
     */
    public Task unmarkTask(int index) throws TaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNumberException();
        }
        Task unmarkedTask = tasks.get(index);
        unmarkedTask.unmark();
        return unmarkedTask;
    }

    /**
     * Adds a Task to the back of the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task specified by the given index.
     *
     * @param index position of the task in the list.
     * @return the Task deleted.
     * @throws TaskNumberException if given index does not correspond to an element in the tasklist.
     */
    public Task deleteTask(int index) throws TaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNumberException();
        }
        return tasks.remove(index);
    }

}
