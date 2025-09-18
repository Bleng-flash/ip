package balloon.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a date which can be represented as a String, a LocalDate, or a LocalDateTime.
 * Objects of this class will be treated as a LocalDate or a LocalDateTime if they can fit their
 * format; otherwise by default they will be treated as a String.
 * <p>
 * This class is useful for the {@link Deadline} and {@link Event} tasks.
 */

public class StringDateTime {

    private static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private enum OutputFormat {
        STRING, DATE, DATE_TIME;
    }

    private String str; // Fallback if user did not pass in a valid date
    private LocalDateTime dateTime; // Parsed LocalDateTime if valid
    private OutputFormat format;

    public StringDateTime(String input) {
        str = input;
        parseString(input);
    }

    /**
     * Parses the input string and sets the appropriate format and dateTime fields.
     *
     * @param input the string to parse
     */
    public void parseString(String input) {
        try {
            dateTime = LocalDateTime.parse(input, INPUT_DATE_TIME_FORMAT);
            format = OutputFormat.DATE_TIME;
        } catch (DateTimeParseException e) {
            try {
                LocalDate dateOnly = LocalDate.parse(input, INPUT_DATE_FORMAT);
                dateTime = dateOnly.atStartOfDay();
                format = OutputFormat.DATE;
            } catch (DateTimeParseException e1) {
                dateTime = null;
                format = OutputFormat.STRING;
            }
        }
    }

    /**
     * Returns a user-friendly string representation of the date/time.
     *
     * @return formatted string according to type (STRING, DATE, or DATE_TIME)
     */
    public String getOutputString() {
        switch (format) {
        case STRING:
            return str;
        case DATE:
            return dateTime.format(OUTPUT_DATE_FORMAT);
        case DATE_TIME:
            return dateTime.format(OUTPUT_DATE_TIME_FORMAT);
        default:
            return "";
        }
    }

    /**
     * Returns the raw string that was originally passed in.
     *
     * @return raw string
     */
    public String getAsRawString() {
        return str;
    }

    public boolean equals(Object object) {
        if (object instanceof StringDateTime) {
            StringDateTime other = (StringDateTime) object;
            return this.getAsRawString().equals(other.getAsRawString());
        }
        return false;
    }
}
