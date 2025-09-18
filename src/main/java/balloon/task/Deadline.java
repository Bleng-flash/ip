package balloon.task;

import balloon.logic.StringDateTime;

public class Deadline extends Task {

    private StringDateTime by;

    public Deadline(String description, String byInput) {
        super(description);
        by = new StringDateTime(byInput);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getOutputString() + ")";
    }

    @Override
    public String toSaveFormat() {
        return "DEADLINE | " + getDoneStatusIndicator() + " | " + description +
                " | " + by.getAsRawString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline) {
            Deadline other = (Deadline) object;
            return this.description.equals(other.description) &&
                    this.by.equals(other.by);
        }
        return false;
    }
}
