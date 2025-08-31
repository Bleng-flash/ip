package balloon.task;

import balloon.StringDateTime;

public class Event extends Task {

    private StringDateTime from;
    private StringDateTime to;


    public Event(String description, String fromInput, String toInput) {
        super(description);
        from = new StringDateTime(fromInput);
        to = new StringDateTime(toInput);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getOutputString() +
                " to: " + to.getOutputString() + ")";
    }

    @Override
    public String toSaveFormat() {
        return "EVENT | " + getDoneStatusIndicator() + " | " + description + " | " +
                from.getAsRawString() + " | " + to.getAsRawString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Event) {
            Event other = (Event) object;
            return this.description.equals(other.description) &&
                    this.from.equals(other.from) &&
                    this.to.equals(other.to);
        }
        return false;
    }
}
