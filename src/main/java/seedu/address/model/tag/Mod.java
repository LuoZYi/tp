package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Mod in the address book.
 */
public class Mod extends Label {
    public static final String MESSAGE_CONSTRAINTS = "Mod codes should start with 2-3 alphabets, "
            + "followed by 4 numbers, and optionally end with an alphabet.";

    // 2-3 alphabets, followed by 4 digits, and optionally ending with an alphabet
    public static final String VALIDATION_REGEX = "^[A-Za-z]{2,3}\\d{4}[A-Za-z]?$";

    /**
     * Constructs a {@code Mod}.
     *
     * @param name A mod name.
     */
    public Mod(String name) {
        super(name);
    }
    /**
     * Factory method to construct a {@code Mod}.
     *
     * @param name A valid mod name.
     */
    public static Mod of(String name) {
        requireNonNull(name);
        checkArgument(isValidModName(name), MESSAGE_CONSTRAINTS);
        return new Mod(name.toUpperCase()); // Mods can only have uppercase letters
    }

    /**
     * Returns true if a given string is a valid mod name.
     */
    public static boolean isValidModName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Mod)) {
            return false;
        }

        Mod otherMod = (Mod) other;
        return name.equals(otherMod.name);
    }
}
