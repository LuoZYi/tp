package seedu.address.model.availability;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a period of time
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeInterval(LocalTime, LocalTime)}
 */
public class TimeInterval {
    public static final String MESSAGE_CONSTRAINTS =
            "TA's free time should have a start and end time in HH:mm format";
    public final LocalTime from;
    public final LocalTime to;

    /**
     * Constructs a {@code TimeInterval}.
     *
     * @param from Start time.
     * @param to   End time.
     */
    public TimeInterval(LocalTime from, LocalTime to) {
        requireNonNull(from);
        requireNonNull(to);
        checkArgument(isValidTimeInterval(from, to), MESSAGE_CONSTRAINTS);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns true if given start and end time is valid.
     */
    public static boolean isValidTimeInterval(LocalTime from, LocalTime to) {
        return to.isAfter(from);
    }

    /**
     * Returns string representation of from time.
     *
     * @return From time in HH:mm format.
     */
    public String getFrom() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return from.format(dtf);
    }

    /**
     * Returns string representation of to time.
     *
     * @return To time in HH:mm format.
     */
    public String getTo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return to.format(dtf);
    }

    @Override
    public String toString() {
        return String.format("%s-%s", from, to);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimeInterval)) {
            return false;
        }

        TimeInterval otherInterval = (TimeInterval) other;

        return from.equals(otherInterval.from) && to.equals(otherInterval.to);
    }
}
