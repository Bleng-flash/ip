public class Task {
    private String description;
    private boolean doneFlag = false;

    public Task(String desc) {
        this.description = desc;
    }

    @Override
    public String toString() {
        String doneIndicator = doneFlag ? "[X] " : "[ ] ";
        return doneIndicator + description;
    }

    public void markAsDone() {
        doneFlag = true;
    }

    public void unmark() {
        doneFlag = false;
    }
}
