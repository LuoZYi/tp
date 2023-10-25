package seedu.address.model.availability;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


/**
 * Represents a Person's free time of the day in the TAM.
 */
public class FreeTime {
    public static final FreeTime EMPTY_FREE_TIME = new FreeTime();
    public static final String MESSAGE_CONSTRAINTS =
            "TA's free time should have a start and end time in HH:mm format";
    public static final int NUM_DAYS = 5;
    public final ArrayList<TimeInterval> intervals = new ArrayList<>(Collections.nCopies(NUM_DAYS, null));

    /**
     * Constructs a {@code FreeTime}.
     * Will assume that the availability is the same of all days of the week.
     *
     * @param from Start time.
     * @param to   End time.
     */
    public FreeTime(LocalTime from, LocalTime to) {
        for (int i = 0; i < NUM_DAYS; i++) {
            this.intervals.set(i, new TimeInterval(from, to));
        }
    }

    /**
     * Empty FreeTime
     */
    private FreeTime() {
    }


    /**
     * Initializes {@code FreeTime} with availability.
     *
     * @param intervals {@code List} of {@code TimeIntervals} which represent availability.
     */
    public FreeTime(List<TimeInterval> intervals) {
        for (int i = 0; i < NUM_DAYS; i++) {
            this.intervals.set(i, intervals.get(i));
        }
    }


    /**
     * Returns true if given start and end time is valid.
     */
    public static boolean isValidFreeTime(List<TimeInterval> intervals) {
        return intervals.size() == NUM_DAYS;
    }

    /**
     * Returns availability on all days of the week.
     *
     * @return an array of {@code TimeInterval}.
     */
    public ArrayList<TimeInterval> getIntervals() {
        assert this.intervals.size() == NUM_DAYS;
        return this.intervals;
    }

    /**
     * Returns availability of a specific day of the week.
     *
     * @param day day of the week, Monday - 1.
     * @return Availability represented by {@code TimeInterval}.
     */
    public TimeInterval getDay(int day) {
        return this.intervals.get(day);
    }


    @Override
    public String toString() {
        if (this.equals(FreeTime.EMPTY_FREE_TIME)) {
            return " ";
        }
        StringBuilder str = new StringBuilder("\n");

        for (int i = 0; i < NUM_DAYS; i++) {
            String day = DayOfWeek.of(i + 1).getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            str.append(String.format("%s: %s\n", day, this.intervals.get(i)));
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (this == EMPTY_FREE_TIME) {
            return false;
        }

        // instanceof handles nulls
        if (!(other instanceof FreeTime)) {
            return false;
        }

        FreeTime otherFreeTime = (FreeTime) other;

        for (int i = 0; i < NUM_DAYS; i++) {
            TimeInterval thisInterval = this.intervals.get(i);
            TimeInterval otherInterval = otherFreeTime.intervals.get(i);
            if (thisInterval == null && otherInterval == null) {
                continue;
            }
            if (thisInterval == null || otherInterval == null) {
                return false;
            }
            if (!thisInterval.equals(otherInterval)) {
                return false;
            }
        }

        return true;
    }

}
