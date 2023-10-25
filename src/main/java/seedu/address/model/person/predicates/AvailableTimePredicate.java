package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.availability.TimeInterval;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code FreeTime} is within the FreeTime that is given.
 */
public class AvailableTimePredicate implements Predicate<Person> {
    private final TimeInterval interval;

    public AvailableTimePredicate(TimeInterval interval) {
        this.interval = interval;
    }

    @Override
    public boolean test(Person person) {
        if (person.getFreeTime() == null) {
            return false;
        }
        return person.getFreeTime().getIntervals().stream()
                .anyMatch(personInterval -> {
                    if (personInterval != null) {
                        return personInterval.isInBetween(interval);
                    }
                    return false;
                });
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AvailableTimePredicate)) {
            return false;
        }

        AvailableTimePredicate otherNameContainsKeywordsPredicate = (AvailableTimePredicate) other;
        return interval.equals(otherNameContainsKeywordsPredicate.interval);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("free time", interval).toString();
    }
}
