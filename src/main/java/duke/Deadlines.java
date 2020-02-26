package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Task item class of the Duke project
 * Extends Task class
 */
public class Deadlines extends Task {

    protected LocalDate by;

    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String showSearch() {
        return "[E]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}