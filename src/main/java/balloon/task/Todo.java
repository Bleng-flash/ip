package balloon.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "TODO | " + getDoneStatusIndicator() + " | " + description;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Todo) {
            Todo other = (Todo) object;
            return this.description.equals(other.description);
        }
        return false;
    }
}
