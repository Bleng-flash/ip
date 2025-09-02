package balloon.task;

/**
 * Represents a task. This is an abstract class.
 * A task can either be a todo, a deadline or an event.
 */
public abstract class Task {

    protected String description;
    private boolean isDone = false;

    public Task(String desc) {
        this.description = desc;
    }

    @Override
    public String toString() {
        String doneIndicator = isDone ? "[X] " : "[ ] ";
        return doneIndicator + description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    protected String getDoneStatusIndicator() {
        return (isDone ? "1" : "0");
    }

    /**
     *
     * @return a line that represents this Task and would be written to the save file.
     */
    public abstract String toSaveFormat();

    /**
     * Returns true if the description of this Task contains word; false otherwise.
     */
    public boolean containsWord(String word) {
        return description.contains(word);
    }
}
