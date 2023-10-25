package seedu.address.storage;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.availability.TimeInterval;

/**
 * TAManager-friendly version of {@link FreeTime}.
 */
public class JsonAdaptedTimeInterval {
    private final String interval;

    /**
     * Constructs a {@code JsonAdaptedTimeInterval} with the given {@code interval}.
     */
    @JsonCreator
    public JsonAdaptedTimeInterval(String interval) {
        this.interval = interval;
    }

    /**
     * Converts a given {@code TimeInterval} into this class for TAManager use.
     */
    public JsonAdaptedTimeInterval(TimeInterval source) {
        interval = source.toString();
    }

    @JsonValue
    public String getInterval() {
        return interval;
    }

    /**
     * Converts this TAManager-friendly adapted mod object into the model's {@code TimeInterval} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted mod.
     */
    public TimeInterval toModelType() throws IllegalValueException, DateTimeParseException {
        String[] splitInterval = interval.split("-");
        LocalTime from = LocalTime.parse(splitInterval[0]);
        LocalTime to = LocalTime.parse(splitInterval[1]);
        if (!TimeInterval.isValidTimeInterval(from, to)) {
            throw new IllegalValueException(TimeInterval.MESSAGE_CONSTRAINTS);
        }
        return new TimeInterval(from, to);
    }
}
