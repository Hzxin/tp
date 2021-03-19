package seedu.address.model.module;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.model.Event;
import seedu.address.model.tag.Tag;

public class Assignment extends Event {
    public static final String MESSAGE_CONSTRAINTS = "Assignment deadline must be formatted "
            + "to a valid DD/MM/YYYY HHmm";

    public final Description description;
    public final LocalDateTime deadline;

    /**
     * Constructs an {@code Assignment}.
     *
     * @param description A valid assignment description.
     * @param deadline A valid date and time.
     */
    public Assignment(Description description, LocalDateTime deadline, Tag tag) {
        super(description, deadline, tag);
        this.description = description;
        this.deadline = deadline;
    }

    public boolean isSameAssignment(Assignment other) {
        return this.equals(other);
    }

    @Override
    public String toString() {
        return description + " due: " + deadline.format(LocalDateTimeUtil.DATETIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && description.equals(((Assignment) other).description)
                && deadline.equals(((Assignment) other).deadline));
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, deadline);
    }
}
