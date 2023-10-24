package seedu.address.model.tag;

/**
 * Represents a Label in the address book.
 * Guarantees: immutable;
 */
public abstract class Label {
    public final String name;

    public Label(String name) {
        this.name = name;
    }


    /**
     * Returns true if a given string matches the name of this label.
     */
    public boolean matchName(String name) {
        return this.name.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + name + ']';
    }
}
