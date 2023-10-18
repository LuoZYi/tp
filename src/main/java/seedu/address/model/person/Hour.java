package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidHour(String)}
 */
public class Hour {

    public static final String MESSAGE_CONSTRAINTS =
            "Hour should only be positive integers and should be less than 9999";
    public static final String VALIDATION_REGEX = "^[0-9]\\d{0,3}$";
    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param hour A valid hour.
     */
    public Hour(String hour) {
        requireNonNull(hour);
        checkArgument(isValidHour(hour), MESSAGE_CONSTRAINTS);
        this.value = hour;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidHour(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Hour)) {
            return false;
        }

        Hour otherHour = (Hour) other;
        return value.equals(otherHour.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

