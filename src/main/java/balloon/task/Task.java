package balloon.task;

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

    public abstract String toSaveFormat();

    /**
     * Returns true if the description of this Task contains word; false otherwise.
     */
    public boolean containsWord(String word) {
        return description.contains(word);
    }
}
