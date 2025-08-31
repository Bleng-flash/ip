package balloon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import balloon.task.Deadline;
import balloon.task.Todo;

public class StorageTest {
    @Test
    public void parseLine_illegalArgument_exceptionThrown() {
        String line = "UNKNOWN | 1 | description";
        try {
            Storage storage = new Storage();
            storage.parseLine(line);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown task type in save file: UNKNOWN",
                    e.getMessage());
        }
    }

    @Test
    public void parseLine_success() {
        String line = "DEADLINE | 0 | CS2103T iP homework | 2025-09-01 2359";
        try {
            Storage storage = new Storage();
            assertEquals(new Deadline("CS2103T iP homework", "2025-09-01 2359"),
                    storage.parseLine(line));
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
}
