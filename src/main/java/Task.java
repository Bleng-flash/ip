public class Task {
    private String name;
    private boolean doneFlag = false;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String doneIndicator = doneFlag ? "[X] " : "[ ] ";
        return doneIndicator + name;
    }

    public void markAsDone() {
        doneFlag = true;
    }

    public void unmark() {
        doneFlag = false;
    }
}
