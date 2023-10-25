package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Mod;

/**
 * Tests that a {@code Person}'s {@code Mods} matches any of the keywords given.
 */
public class TeachingModPredicate implements Predicate<Person> {
    private final List<Mod> mods;

    public TeachingModPredicate(List<Mod> mods) {
        this.mods = mods;
    }

    @Override
    public boolean test(Person person) {
        return mods.stream()
                .anyMatch(predicateMod -> person.getMods().stream()
                        .anyMatch(predicateMod::equals));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TeachingModPredicate)) {
            return false;
        }

        TeachingModPredicate otherNameContainsKeywordsPredicate = (TeachingModPredicate) other;
        return mods.equals(otherNameContainsKeywordsPredicate.mods);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("mods", mods).toString();
    }
}
